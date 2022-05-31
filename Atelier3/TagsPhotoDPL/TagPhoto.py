
import flask
from flask import request, jsonify
import numpy as np
import tensorflow as tf
import cv2
import json
import urllib
import requests
from io import BytesIO
from PIL import Image

def url_to_img(url, save_as=''):
  img = Image.open(BytesIO(requests.get(url).content))
  if save_as:
    img.save(save_as)
  return np.array(img)


def read_label_map(id):
    
    item_id = None
    item_name = None
    items = {}
    
    with open(label_map_path, "r") as file:
        for line in file:
            line.replace(" ", "")
            if line == "item{":
                pass
            elif line == "}":
                pass
            elif "id" in line:
                item_id = int(line.split(":", 1)[1].strip())
            elif "display_name" in line:
                item_name = line.split(":", 1)[1].replace("'", "").strip()

            if item_id is not None and item_name is not None:
                if(item_id == (id+1)):
                    items = item_name
                    
                    item_id = None
                    item_name = None

                    return items
label_map_path = "label_map.pbtxt"
test = read_label_map(17)
print(test)
app = flask.Flask(__name__)
app.config["DEBUG"] = True

model= tf.saved_model.load("faster_rcnn/saved_model")


@app.route('/', methods=['GET'])
def home():
    return '''<h1>Annuaire Internet</h1>
<p>Ce site est le prototype d’une API mettant à disposition des données sur les employés d’une entreprise.</p>'''
 
 
@app.route('/api/v1/resources/employees/all', methods=['GET'])
def api_all():
    return jsonify(employees)
 
 
@app.route('/api/v1/resources/photo', methods=['GET'])
def api_url():
    print("oui")
    if "url" in request.args:
        print(request.args)
        url = (request.args['url'])
        #https://cdn.paris.fr/paris/2020/02/20/original-b9dd32cf72063e2f7e2425a77107749f.jpg
        img = url_to_img(url)
        tagslist= []
        img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        print("image dl")
        input_tensor = np.expand_dims(img, 0)

        resp = model(input_tensor)
        for boxes, classes, scores in zip(resp['detection_boxes'].numpy(), resp['detection_classes'], resp['detection_scores'].numpy()):
            for box, cls, score in zip(boxes, classes, scores):
                if score > 0.8: 
                    print(box)
                    print(cls)
                    print(score)
                    dimensions = img.shape

                    h = img.shape[0]
                    w= img.shape[1]
                    ymin = int(box[0] * h)
                    xmin = int(box[1] * w)
                    ymax = int(box[2] * h)
                    xmax = int(box[3] * w)
                    tag = read_label_map(cls)
                    print("tag")
                    cv2.putText(img, tag, (xmin, ymin-10), cv2.FONT_HERSHEY_SIMPLEX, 1, (255, 0, 0), 1)
                
                    
                    tag = tag.replace('"', '')
                    tag = tag.replace('\\', '')
                    tag = tag.replace('"', '')
                    if tag not in tagslist:
                        tagslist.append(tag)
                    
                    cv2.rectangle(img, (xmin, ymin), (xmax, ymax), (128, 0, 128), 4)

        # convert back to bgr and save image
        cv2.imwrite("output.png", cv2.cvtColor(img, cv2.COLOR_RGB2BGR))
        data_set = {"tags": tagslist}
        
        print("fin")
        json_dump = json.dumps(data_set)
        print(json_dump)
        return jsonify(data_set)
        
    else:
        return "Erreur: Pas d’identifiant fourni. Veuillez spécifier une url valide."
 
 
app.run()
