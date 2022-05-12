--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

-- Started on 2022-05-12 14:37:03

SET statement_timeout = 0;
SET lock_timeout = 0;
--

CREATE TABLE public."Card" (
    id integer NOT NULL,
    name text,
    description text,
    family text,
    affinity text,
    attack numeric,
    defense numeric,
    hp numeric,
    energy numeric,
    price numeric,
    "imageUrl" text
);


ALTER TABLE public."Card" OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16843)
-- Name: Transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Transaction" (
    id integer NOT NULL,
    card_id integer,
    seller_id integer,
    date_creation date,
    buyer_id integer,
    date_buy date
);


ALTER TABLE public."Transaction" OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16829)
-- Name: User; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."User" (
    id integer NOT NULL,
    name text,
    surname text,
    lastname text,
    password text
);


ALTER TABLE public."User" OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16848)
-- Name: user_card; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_card (
    card_id integer NOT NULL,
    user_id integer NOT NULL,
    quantity integer
);


ALTER TABLE public.user_card OWNER TO postgres;

--
-- TOC entry 3178 (class 2606 OID 16842)
-- Name: Card Card_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Card"
    ADD CONSTRAINT "Card_pkey" PRIMARY KEY (id);


--
-- TOC entry 3180 (class 2606 OID 16847)
-- Name: Transaction Transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Transaction"
    ADD CONSTRAINT "Transaction_pkey" PRIMARY KEY (id);


--
-- TOC entry 3176 (class 2606 OID 16835)
-- Name: User User_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (id);


--
-- TOC entry 3182 (class 2606 OID 16852)
-- Name: user_card user_card_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_card
    ADD CONSTRAINT user_card_pkey PRIMARY KEY (card_id, user_id);


--
-- TOC entry 3185 (class 2606 OID 16873)
-- Name: Transaction fk_buyer_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Transaction"
    ADD CONSTRAINT fk_buyer_id FOREIGN KEY (buyer_id) REFERENCES public."User"(id) NOT VALID;


--
-- TOC entry 3186 (class 2606 OID 16853)
-- Name: user_card fk_card_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_card
    ADD CONSTRAINT fk_card_id FOREIGN KEY (card_id) REFERENCES public."Card"(id);


--
-- TOC entry 3183 (class 2606 OID 16863)
-- Name: Transaction fk_card_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Transaction"
    ADD CONSTRAINT fk_card_id FOREIGN KEY (card_id) REFERENCES public."Card"(id) NOT VALID;


--
-- TOC entry 3184 (class 2606 OID 16868)
-- Name: Transaction fk_seller_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Transaction"
    ADD CONSTRAINT fk_seller_id FOREIGN KEY (seller_id) REFERENCES public."User"(id) NOT VALID;


--
-- TOC entry 3187 (class 2606 OID 16858)
-- Name: user_card fk_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_card
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public."User"(id);


-- Completed on 2022-05-12 14:37:04

--
-- PostgreSQL database dump complete
--

