--
-- PostgreSQL database dump
--

-- Dumped from database version 11.4
-- Dumped by pg_dump version 11.4

-- Started on 2019-09-20 17:17:13

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 34427)
-- Name: ec_brand; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ec_brand (
    id integer NOT NULL,
    brand_title character varying NOT NULL
);


ALTER TABLE public.ec_brand OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 34433)
-- Name: ec_brand_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.ec_brand ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.ec_brand_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 198 (class 1259 OID 34435)
-- Name: ec_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ec_category (
    id integer NOT NULL,
    title character varying NOT NULL
);


ALTER TABLE public.ec_category OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 34441)
-- Name: ec_category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.ec_category ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.ec_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 200 (class 1259 OID 34443)
-- Name: ec_pictures; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ec_pictures (
    id integer NOT NULL,
    title character varying NOT NULL,
    file_path character varying NOT NULL,
    product_id integer NOT NULL
);


ALTER TABLE public.ec_pictures OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 34449)
-- Name: ec_pictures_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.ec_pictures ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.ec_pictures_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 202 (class 1259 OID 34451)
-- Name: ec_product_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ec_product_type (
    id integer NOT NULL,
    category_id integer NOT NULL,
    brand_id integer NOT NULL,
    model character varying NOT NULL,
    variant character varying,
    gearing_type character varying NOT NULL,
    age integer NOT NULL,
    weight_kg double precision NOT NULL,
    power_ps integer NOT NULL,
    engine character varying NOT NULL,
    cubic_capacity_cm3 integer NOT NULL,
    nm double precision NOT NULL,
    drive_system character varying NOT NULL,
    fuel_type character varying NOT NULL,
    zero_to_hundred double precision NOT NULL,
    seats integer NOT NULL,
    doors integer NOT NULL,
    add_driver boolean NOT NULL,
    minimum_age integer NOT NULL,
    body_color character varying NOT NULL,
    upholstery character varying NOT NULL,
    massage_seats boolean NOT NULL,
    displays_rear boolean NOT NULL,
    price_200 double precision NOT NULL,
    price_500 double precision NOT NULL,
    price_1000 double precision NOT NULL,
    blocked_when character varying,
    highlighted boolean NOT NULL
);


ALTER TABLE public.ec_product_type OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 34457)
-- Name: ec_product_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.ec_product_type ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.ec_product_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 204 (class 1259 OID 34459)
-- Name: ec_search_words; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ec_search_words (
    id integer NOT NULL,
    user_id integer NOT NULL,
    search_words character varying NOT NULL
);


ALTER TABLE public.ec_search_words OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 34465)
-- Name: ec_search_words_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.ec_search_words ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.ec_search_words_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 206 (class 1259 OID 34467)
-- Name: ec_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ec_user (
    id integer NOT NULL,
    role character varying,
    name character varying,
    firstname character varying,
    gender character(1),
    e_mail character varying NOT NULL,
    password character varying(255) NOT NULL,
    title character varying,
    birth date,
    street character varying,
    house_number integer,
    post_code integer,
    city character varying
);


ALTER TABLE public.ec_user OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 34473)
-- Name: ec_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.ec_user ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.ec_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 2860 (class 0 OID 34427)
-- Dependencies: 196
-- Data for Name: ec_brand; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ec_brand (id, brand_title) OVERRIDING SYSTEM VALUE VALUES (1, 'Range Rover');
INSERT INTO public.ec_brand (id, brand_title) OVERRIDING SYSTEM VALUE VALUES (2, 'Lamborghini');
INSERT INTO public.ec_brand (id, brand_title) OVERRIDING SYSTEM VALUE VALUES (3, 'Honda');
INSERT INTO public.ec_brand (id, brand_title) OVERRIDING SYSTEM VALUE VALUES (4, 'Cadillac');
INSERT INTO public.ec_brand (id, brand_title) OVERRIDING SYSTEM VALUE VALUES (5, 'Nissan');
INSERT INTO public.ec_brand (id, brand_title) OVERRIDING SYSTEM VALUE VALUES (6, 'Maserati');
INSERT INTO public.ec_brand (id, brand_title) OVERRIDING SYSTEM VALUE VALUES (7, 'Chevrolet');
INSERT INTO public.ec_brand (id, brand_title) OVERRIDING SYSTEM VALUE VALUES (8, 'Aston Martin');
INSERT INTO public.ec_brand (id, brand_title) OVERRIDING SYSTEM VALUE VALUES (9, 'Bentley');
INSERT INTO public.ec_brand (id, brand_title) OVERRIDING SYSTEM VALUE VALUES (10, 'Rolls Royce');
INSERT INTO public.ec_brand (id, brand_title) OVERRIDING SYSTEM VALUE VALUES (11, 'BMW');
INSERT INTO public.ec_brand (id, brand_title) OVERRIDING SYSTEM VALUE VALUES (12, 'Porsche');
INSERT INTO public.ec_brand (id, brand_title) OVERRIDING SYSTEM VALUE VALUES (13, 'Mercedes');
INSERT INTO public.ec_brand (id, brand_title) OVERRIDING SYSTEM VALUE VALUES (14, 'Audi');
INSERT INTO public.ec_brand (id, brand_title) OVERRIDING SYSTEM VALUE VALUES (15, 'Ferrari');
INSERT INTO public.ec_brand (id, brand_title) OVERRIDING SYSTEM VALUE VALUES (16, 'Jaguar');


--
-- TOC entry 2862 (class 0 OID 34435)
-- Dependencies: 198
-- Data for Name: ec_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ec_category (id, title) OVERRIDING SYSTEM VALUE VALUES (1, 'Sport');
INSERT INTO public.ec_category (id, title) OVERRIDING SYSTEM VALUE VALUES (2, 'Sport+');
INSERT INTO public.ec_category (id, title) OVERRIDING SYSTEM VALUE VALUES (3, 'Luxus');
INSERT INTO public.ec_category (id, title) OVERRIDING SYSTEM VALUE VALUES (4, 'SUV');


--
-- TOC entry 2864 (class 0 OID 34443)
-- Dependencies: 200
-- Data for Name: ec_pictures; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (1, 'Nissan GTR Nismo', '/uploads/1/Nissan_GTR.jpg', 1);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (2, 'Lamborghini Aventador', '/uploads/2/Lamborghini_Aventador_S.jpg', 2);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (3, 'Lamborghini Aventador SV', '/uploads/3/Lamborghini_Aventador_sv.jpg', 3);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (4, 'Lamborghini Aventador SV', '/uploads/3/Lamborghini_Aventador_sv2.jpg', 3);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (5, 'Lamborghini Huracán Performante Spyder', '/uploads/4/Lamborghini_Huracán_Performante_Spyder.jpg', 4);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (6, 'Lamborghini Huracán Performante Spyder', '/uploads/4/Lamborghini_Huracán_Performante_Spyder2.jpg', 4);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (7, 'Lamborghini Huracán', '/uploads/5/Lamborghini_Huracan.jpg', 5);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (8, 'Lamborghini Huracán', '/uploads/5/Lamborghini_Huracan2.jpg', 5);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (9, 'Ferrari 458', '/uploads/6/Ferrari_458_Italia.jpg', 6);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (10, 'Ferrari 488 Pista', '/uploads/7/Ferrari_488_Pista.jpg', 7);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (11, 'Ferrari 488 Pista', '/uploads/7/Ferrari_488_Pista2.jpg', 7);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (12, 'Ferrari F12', '/uploads/8/Ferrari_F12_Berlinetta.jpg', 8);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (13, 'Ferrari F12', '/uploads/8/Ferrari_F12_Berlinetta2.jpg', 8);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (14, 'Ferrari GTC4Lusso', '/uploads/9/Ferrari_GTC4Lusso.jpg', 9);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (15, 'Ferrari 812 Superfast', '/uploads/10/Ferrari_812_superfast.jpg', 10);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (16, 'Porsche Turbo S', '/uploads/11/Porsche_911_Turbo_S.jpg', 11);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (17, 'Porsche Gt3 RS', '/uploads/12/Porsche_Gt3_RS.jpg', 12);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (18, 'Porsche Gt3 RS', '/uploads/12/Porsche_Gt3_RS2.jpg', 12);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (19, 'Porsche Gt3 RS', '/uploads/12/Porsche_Gt3_RS3.jpg', 12);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (20, 'Porsche GT2 RS', '/uploads/13/Porsche_GT2_RS.jpg', 13);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (21, 'Audi R8', '/uploads/14/Audi_R8.jpeg', 14);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (22, 'Mercedes SLS', '/uploads/15/Mercedes-Benz_SLS_AMG.jpg', 15);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (23, 'Mercedes GTS ', '/uploads/16/Mercedes_AMG_GT_S.jpg', 16);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (24, 'Mercedes S63 AMG coupé', '/uploads/17/Mercedes_AMG_S_63_Coupe.jpg', 17);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (25, 'Corvette Z06 Cabrio', '/uploads/18/Corvette_Z06_Cabrio.jpg', 18);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (26, 'Aston Martin DB11', '/uploads/19/Aston_Martin_DB11.jpg', 19);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (27, 'Aston Martin Vent', '/uploads/20/Aston_Martin_Vent.JPG', 20);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (28, 'Aston Martin DBS Superleggera', '/uploads/21/Aston_Martin_DBS_Superleggera.jpg', 21);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (29, 'Aston Martin DBS Superleggera', '/uploads/21/Aston_Martin_DBS_Superleggera.jpg', 21);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (30, 'Porsche 918 Spyder', '/uploads/22/Porsche_918_Spyder.jpg', 22);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (31, 'BMW i8 Roadster', '/uploads/23/BMW_i8_Roadster.jpg', 23);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (32, 'BMW i8', '/uploads/24/BMW_i8.jpg', 24);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (33, 'Honda NSX', '/uploads/25/Honda_NSX.jpg', 25);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (34, 'Porsche Panamera 4 E-Hybrid', '/uploads/26/Porsche_Panamera_Sport_Turismo_e-hybrid.jpg', 26);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (35, 'Porsche Panamera 4 E-Hybrid', '/uploads/26/Porsche_Panamera_Sport_Turismo_e-hybrid2.jpg', 26);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (36, 'Bentley Continental GT', '/uploads/27/Bentley_Continental_GT.jpg', 27);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (37, 'Mercedes Benz S63 AMG', '/uploads/28/Mercedes_Benz_S63_AMG.jpg', 28);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (38, 'Mercedes Benz 500 L', '/uploads/29/Mercedes_Benz_500_L.jpg', 29);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (39, 'Mercedes S600 Maybach', '/uploads/30/Mercedes_S600_Maybach.jpg', 30);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (40, 'Mercedes S600 Maybach', '/uploads/30/Mercedes_S600_Maybach2.jpg', 30);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (41, 'Mercedes S600 Maybach', '/uploads/30/Mercedes_S600_Maybach3.jpg', 30);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (42, 'Rolls-Royce Phantom', '/uploads/31/Rolls_Royce_Phantom.jpg', 31);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (43, 'Rolls-Royce Phantom', '/uploads/31/Rolls_Royce_Phantom2.jpeg', 31);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (44, 'Rolls-Royce Wraith', '/uploads/32/Rolls_Royce_Wraith.jpg', 32);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (45, 'Porsche Panamera Turbo', '/uploads/33/Porsche_Panamera_Sport_Turismo.jpg', 33);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (46, 'Bentley Flying Spur', '/uploads/34/Bentley_Flying_Spur.jpg', 34);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (47, 'Bentley Mulsanne Speed', '/uploads/35/Bentley_Mulsanne_Speed.jpg', 35);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (48, 'Maserati Quattroporte GTS', '/uploads/36/Maserati_Quattroporte.jpg', 36);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (49, 'Maserati Quattroporte GTS', '/uploads/36/Maserati_Quattroporte2.jpg', 36);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (50, 'Jaguar XJR', '/uploads/37/Jaguar_XJR.jpg', 37);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (51, 'BMW M760Li', '/uploads/38/BMW_M760Li.jpg', 38);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (52, 'BMW 750', '/uploads/39/BMW_750Li.jpg', 39);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (53, 'Audi A8', '/uploads/40/Audi_A8.jpg', 40);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (54, 'Audi S8 Plus', '/uploads/41/Audi_S8_Plus.jpg', 41);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (55, 'Audi S8 Plus', '/uploads/41/Audi_S8_Plus2.jpg', 41);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (56, 'Cadillac CTS-V', '/uploads/42/Cadillac_CTS_V.jpg', 42);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (57, 'Bentley Bentega', '/uploads/43/Bentley_Bentayga.jpg', 43);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (58, 'Maserati Levante', '/uploads/44/Maserati_Levante.jpg', 44);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (59, 'Rolls Royce Cullinan', '/uploads/45/Rolls_Royce_Cullinan.jpg', 45);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (60, 'Cadillac Escalade', '/uploads/46/Cadillac_Escalade.jpeg', 46);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (61, 'Cadillac Escalade', '/uploads/46/Cadillac_Escalade2.jpg', 46);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (62, 'Audi SQ8', '/uploads/47/Audi_SQ81.jpg', 47);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (63, 'Mercedes G 500', '/uploads/48/Mercedes_G_500.jpg', 48);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (64, 'Mercedes G 63', '/uploads/49/Mercedes_AMG_G_63.jpg', 49);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (65, 'BMW X5 M50', '/uploads/50/BMW_X5__M50.jpg', 50);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (66, 'Range Rover Sport SVR', '/uploads/51/Range_Rover_Sport_SVR.jpg', 51);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (67, 'BMW X7', '/uploads/52/BMW_X7.jpg', 52);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (68, 'Mercedes GLS', '/uploads/53/Mercedes_GLS.jpg', 53);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (69, 'Porsche Cayenne', '/uploads/54/Porsche_Cayenne.jpg', 54);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (70, 'Porsche Macan Turbo', '/uploads/55/Porsche_Macan_Turbo.jpg', 55);
INSERT INTO public.ec_pictures (id, title, file_path, product_id) OVERRIDING SYSTEM VALUE VALUES (71, 'Lamborghini Urus', '/uploads/56/Lamborghini_Urus.jpg', 56);


--
-- TOC entry 2866 (class 0 OID 34451)
-- Dependencies: 202
-- Data for Name: ec_product_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (1, 1, 5, 'GTR', 'Nismo', 'Manuell', 2012, 1815, 550, '3,8 l V6', 3799, 632, 'Allrad', 'Benzin', 2.70000000000000018, 4, 2, false, 25, 'weiß', 'Leder', false, false, 599, 799, 999, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (2, 1, 2, 'Aventador', 'Roadster', 'Automatik', 1575, 1625, 700, '12-Zylinder V-Motor', 6498, 690, 'Allrad', 'Benzin', 2.89999999999999991, 2, 2, false, 25, 'Blu Aegir', 'Leder', false, false, 999, 1199, 1399, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (3, 1, 2, 'Aventador', 'SV', 'Automatik', 2015, 1525, 750, '12-Zylinder V-Motor', 6498, 690, 'Allrad', 'Benzin', 3, 2, 2, false, 25, 'Red Glitter', 'Alcantara', false, false, 1199, 1399, 1599, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (4, 1, 2, 'Huracán', '', 'Manuell', 2014, 1422, 610, '10-Zylinder V-Motor', 5204, 560, 'Allrad', 'Benzin', 3.20000000000000018, 2, 2, false, 25, 'Schwarz-Matt', 'Alcantara', false, false, 699, 899, 1099, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (5, 1, 2, 'Huracán', 'Performante Spyder', 'Manuell', 2016, 1542, 610, '10-Zylinder V-Motor', 5204, 560, 'Allrad', 'Benzin', 3.39999999999999991, 2, 2, false, 25, 'Blau', 'Alcantara', false, false, 799, 999, 1199, NULL, true);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (6, 1, 15, '488', 'Pista', 'Manuell', 2018, 1385, 720, '8-Zylinder V-Motor', 3902, 770, 'Allrad', 'Benzin', 2.79999999999999982, 2, 2, true, 25, 'Rot', 'Alcantara', false, false, 899, 1099, 1399, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (7, 1, 15, '458', 'Italia', 'Manuell', 2010, 1485, 570, '8-Zylinder V-Motor', 4499, 540, 'Allrad', 'Benzin', 3.39999999999999991, 2, 2, true, 23, 'Rot', 'Leder', false, false, 599, 799, 999, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (8, 1, 15, 'F12', 'Berlinetta', 'Manuell', 2012, 1630, 741, '12-Zylinder V-Motor', 6262, 690, 'Allrad', 'Benzin', 3.20000000000000018, 2, 2, true, 23, 'Rot', 'Leder', false, false, 699, 899, 1099, NULL, true);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (9, 1, 15, 'GTC4', 'Lusso', 'Manuell', 2015, 1920, 690, '12-Zylinder V-Motor', 6262, 697, 'Allrad', 'Benzin', 3.39999999999999991, 2, 4, true, 23, 'Grau-Matt', 'Leder', false, false, 799, 999, 1199, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (10, 1, 15, '812', 'Superfast', 'Manuell', 2018, 1630, 800, '12-Zylinder V-Motor', 6496, 718, 'Allrad', 'Benzin', 2.89999999999999991, 2, 2, false, 25, 'Rot', 'Leder', false, false, 899, 1099, 1299, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (11, 1, 12, 'Turbo', 'S', 'Manuell', 2016, 1675, 580, '6-Zylinder Boxermotor', 3800, 50, 'Allrad', 'Benzin', 2.89999999999999991, 2, 2, false, 23, 'Blau', 'Leder', false, false, 399, 499, 699, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (12, 1, 12, 'Gt3', 'RS', 'Manuell', 2018, 1505, 520, '6-Zylinder Boxermotor', 3996, 470, 'Allrad', 'Benzin', 3.39999999999999991, 2, 2, true, 25, 'Lizardgrün', 'Leder', false, false, 699, 899, 1099, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (13, 1, 12, 'GT2', 'RS', 'Manuell', 2016, 1445, 544, '6-Zylinder Boxermotor', 3600, 798, 'Frontantrieb', 'Benzin', 3.5, 2, 2, false, 21, 'Silber', 'Leder', false, false, 899, 1099, 1299, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (14, 1, 14, 'R8', 'Coupe', 'Manuell', 2015, 1715, 540, '10-Zylinder V-Motor', 5204, 540, 'Allrad', 'Benzin', 3.5, 2, 2, false, 23, 'weiß', 'Leder', false, false, 599, 799, 999, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (15, 1, 13, 'SLS', '', 'Manuell', 2009, 1695, 571, '8-Zylinder V-Motor', 6208, 650, 'Allrad', 'Benzin', 3.79999999999999982, 2, 2, true, 23, 'Silber', 'Leder', false, false, 799, 999, 1199, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (16, 1, 13, 'GT', 'S', 'Manuell', 2015, 1645, 510, '8-Zylinder V-Motor', 3982, 650, 'Allrad', 'Benzin', 3.60000000000000009, 2, 2, false, 23, 'Rot', 'Schwarz-Matt', true, false, 899, 1099, 1299, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (17, 1, 13, 'S63', 'AMG coupé', 'Automatik', 2015, 2070, 585, '8-Zylinder V-Motor', 5461, 900, 'Allrad', 'Benzin', 5.40000000000000036, 2, 2, false, 25, 'Rot', 'Leder', true, false, 499, 699, 899, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (18, 1, 7, 'Corvette', 'Z06', 'Manuell', 2016, 1659, 659, '8-Zylinder V-Motor', 6162, 881, 'Heckantrieb', 'Benzin', 3.5, 2, 2, true, 25, 'Gelb', 'Leder', false, false, 499, 699, 899, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (19, 1, 8, 'DB11', 'Coupe', 'Manuell', 2016, 1870, 608, '12-Zylinder V-Motor', 5204, 700, 'Heckantrieb', 'Benzin', 3.89999999999999991, 2, 2, true, 25, 'Schwarz', 'Leder', false, false, 599, 799, 999, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (20, 1, 8, 'Vant', 'V8', 'Manuell', 2018, 1530, 510, '8-Zylinder V-Motor', 3982, 685, 'Heckantrieb', 'Benzin', 3.70000000000000018, 2, 2, true, 23, 'Blau', 'Leder', false, false, 599, 799, 999, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (21, 1, 8, 'DBS', 'Superleggera', 'Automatik', 2018, 1693, 725, '12-Zylinder V-Motor', 5204, 900, 'Heckantrieb', 'Benzin', 3.39999999999999991, 2, 2, true, 23, 'Dunkel Rot', 'Leder', false, false, 999, 1199, 1399, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (22, 2, 12, '918', 'Spyder', 'Manuell', 2014, 1750, 608, '8-Zylinder V-Motor', 4593, 540, 'Allrad', 'Benzin', 2.60000000000000009, 2, 2, true, 21, 'Silber', 'Leder', false, false, 1499, 1699, 1899, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (23, 2, 11, 'i8', 'Coupe', 'Automatik', 2018, 1610, 231, '3-Zylinder Reiheotor', 1499, 320, 'Allrad', 'Benzin', 4.40000000000000036, 2, 2, false, 21, 'Silber', 'Leder', false, false, 359, 559, 759, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (24, 2, 11, 'M6', '', 'Manuell', 2013, 1950, 560, '8-Zylinder V-Motor', 4395, 680, 'Heckantrieb', 'Benzin', 4, 5, 4, true, 23, 'Silber', 'Leder', false, false, 399, 599, 799, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (25, 2, 3, 'NSX', '', 'Manuell', 2016, 1776, 507, '6-Zylinder V-Motor', 3493, 550, 'Allrad', 'Benzin', 3.5, 2, 2, false, 23, 'Orange', 'Leder', false, false, 359, 559, 759, NULL, true);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (26, 2, 12, 'Panamera', '4 E-Hybrid', 'Automatik', 2013, 2170, 416, '6-Zylinder V-Motor', 2995, 590, 'Heckantrieb', 'Benzin', 5.59999999999999964, 2, 2, true, 23, 'Grau', 'Leder', false, false, 499, 699, 899, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (27, 3, 9, 'Continental', 'GT', 'Automatik', 2003, 2350, 560, '12-Zylinder W-Motor', 5998, 650, 'Allrad', 'Benzin', 4.20000000000000018, 4, 2, false, 23, 'Blau', 'Leder', false, false, 699, 899, 1099, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (28, 3, 13, 'S63', 'AMG', 'Automatik', 2015, 2070, 585, '8-Zylinder V-Motor', 5461, 900, 'Allrad', 'Benzin', 4.09999999999999964, 4, 2, true, 23, 'Weiß', 'Leder', true, false, 499, 699, 899, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (29, 3, 13, '500', 'L', 'Automatik', 2003, 1895, 306, '8-Zylinder V-Motor', 4966, 460, 'Heckantrieb', 'Benzin', 6.5, 6, 4, true, 21, 'Silber', 'Leder', true, false, 299, 499, 699, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (30, 3, 13, 'S600', 'Maybach', 'Automatik', 2015, 2220, 455, '8-Zylinder V-Motor', 4663, 700, 'Heckantrieb', 'Benzin', 5, 5, 4, true, 21, 'Schwarz', 'Leder', true, true, 899, 1099, 1299, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (31, 3, 10, 'Phantom', '', 'Automatik', 2018, 2560, 320, '12-Zylinder V-Motor', 6749, 900, 'Heckantrieb', 'Benzin', 5.29999999999999982, 5, 4, false, 23, 'Lila', 'Leder', true, true, 1499, 1699, 1899, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (32, 3, 10, 'Wraith', '', 'Automatik', 2013, 2435, 632, '12-Zylinder V-Motor', 6592, 800, 'Heckantrieb', 'Benzin', 4.59999999999999964, 4, 2, false, 25, 'Schwarz', 'Leder', false, false, 1699, 1899, 2099, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (33, 3, 12, 'Panamera', 'Turbo', 'Manuell', 2011, 2045, 500, '8-Zylinder V-Motor', 4806, 700, 'Allrad', 'Benzin', 4.09999999999999964, 4, 4, true, 25, 'Rot', 'Leder', false, false, 459, 659, 859, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (34, 3, 9, 'Flying Spur', '', 'Automatik', 2014, 2425, 507, '8-Zylinder V-Motor', 3993, 660, 'Allrad', 'Benzin', 5.20000000000000018, 5, 4, true, 21, 'Blau-Grau', 'Leder', true, false, 899, 1099, 1299, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (35, 3, 9, 'Mulsanne Speed', '', 'Automatik', 2016, 2685, 537, '8-Zylinder V-Motor', 6752, 1100, 'Heckantrieb', 'Benzin', 4.90000000000000036, 5, 4, false, 23, 'Hell-Blau', 'Leder', true, false, 899, 1099, 1299, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (36, 3, 6, 'Quattroporte', 'GTS', 'Automatik', 2016, 1900, 550, '8-Zylinder V-Motor', 3799, 710, 'Heckantrieb', 'Benzin', 4.70000000000000018, 5, 4, false, 23, 'Weiß', 'Leder', false, false, 299, 499, 699, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (37, 3, 16, 'XJR', 'X308', 'Automatik', 1997, 1775, 363, '8-Zylinder V-Motor', 3996, 505, 'Heckantrieb', 'Benzin', 5.59999999999999964, 5, 4, true, 23, 'Schwarz-Matt', 'Leder', false, false, 299, 499, 699, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (38, 3, 11, 'M760Li', 'xDrive', 'Automatik', 2018, 2070, 612, '8-Zylinder V-Motor', 3982, 900, 'Allrad', 'Benzin', 3.5, 5, 4, true, 21, 'Schwarz-Matt', 'Leder', true, true, 359, 559, 759, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (39, 3, 11, '745Le', '', 'Automatik', 2019, 2110, 286, '6-Zylinder Reiheotor', 2998, 450, 'Frontantrieb', 'Elektro', 5.29999999999999982, 5, 4, false, 25, 'Grau', 'Leder', true, true, 299, 599, 799, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (40, 3, 14, 'A8', '', 'Automatik', 2011, 1970, 420, '8-Zylinder V-Motor', 3993, 600, 'Allrad', 'Benzin', 4.59999999999999964, 5, 4, false, 25, 'Schwarz', 'Leder', true, false, 299, 499, 699, NULL, true);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (41, 3, 14, 'S8', 'Plus', 'Automatik', 2016, 2065, 605, '8-Zylinder V-Motor', 3993, 750, 'Allrad', 'Benzin', 3.60000000000000009, 5, 4, false, 25, 'Silber', 'Leder', true, false, 349, 549, 749, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (42, 3, 4, 'CTS-V', '', 'Automatik', 2015, 1850, 649, '8-Zylinder V-Motor', 6162, 855, 'Heckantrieb', 'Benzin', 3.70000000000000018, 5, 4, false, 23, 'Weiß', 'Leder', false, false, 249, 449, 649, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (43, 4, 9, 'Bentayga', '', 'Automatik', 2016, 2440, 608, '12-Zylinder W-Motor', 5950, 900, 'Allrad', 'Benzin', 4, 5, 4, false, 23, 'Orange', 'Leder', true, true, 1299, 1499, 1699, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (44, 4, 6, 'Levante', '', 'Automatik', 2016, 2109, 430, '6-Zylinder V-Motor', 2979, 580, 'Allrad', 'Benzin', 5.20000000000000018, 5, 4, true, 23, 'Grau-Matt', 'Leder', false, false, 299, 499, 699, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (45, 4, 10, 'Cullinan', '', 'Automatik', 2018, 2660, 571, '12-Zylinder V-Motor', 6749, 850, 'Allrad', 'Benzin', 5.20000000000000018, 4, 4, true, 23, 'Rot', 'Leder', true, false, 1899, 2099, 2299, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (46, 4, 4, 'Escalade', '6.2 V8 ESV', 'Automatik', 2017, 2725, 426, '8-Zylinder V-Motor', 6162, 610, 'Allrad', 'Benzin', 6.90000000000000036, 7, 4, true, 23, 'Dunkel-Grau', 'Leder', false, false, 499, 699, 899, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (47, 4, 14, 'SQ8', 'TDI', 'Automatik', 2018, 2440, 435, 'V8-Zylinder-Dieselmotor', 3956, 900, 'Allrad', 'Diesel', 4.79999999999999982, 5, 4, true, 25, 'Grau', 'Leder', false, false, 449, 649, 849, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (48, 4, 13, 'G 500', '', 'Automatik', 2018, 2429, 422, '8-Zylinder V-Motor', 3982, 610, 'Allrad', 'Benzin', 5.90000000000000036, 5, 4, false, 25, 'Grau-Matt', 'Leder', false, false, 449, 649, 849, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (49, 4, 13, 'G 63', '', 'Automatik', 2015, 2555, 571, '8-Zylinder V-Motor', 5461, 760, 'Allrad', 'Benzin', 5.40000000000000036, 5, 4, false, 23, 'Weiß', 'Leder', false, false, 499, 699, 899, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (50, 4, 11, 'X5', 'M50', 'Automatik', 2018, 2350, 400, '6-Zylinder Reiheotor', 2993, 760, 'Allrad', 'Benzin', 5.20000000000000018, 5, 4, false, 23, 'Silber', 'Leder', false, false, 349, 549, 749, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (51, 4, 1, 'Sport', 'SVR', 'Automatik', 2017, 2330, 550, '8-Zylinder V-Motor', 5000, 680, 'Allrad', 'Benzin', 4.90000000000000036, 5, 4, false, 23, 'Blau', 'Leder', false, false, 359, 559, 759, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (52, 4, 11, 'X7', '', 'Automatik', 2018, 2395, 340, '6-Zylinder Reiheotor', 2998, 450, 'Allrad', 'Benzin', 6.09999999999999964, 7, 4, true, 23, 'Braun', 'Leder', false, false, 369, 569, 769, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (53, 4, 13, 'GLS', '', 'Automatik', 2015, 2445, 333, '6-Zylinder V-Motor', 2996, 480, 'Allrad', 'Benzin', 6.59999999999999964, 7, 4, true, 25, 'Grau', 'Leder', false, false, 549, 749, 949, NULL, true);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (54, 4, 12, 'Cayenne', '', 'Manuell', 2010, 2300, 405, '8-Zylinder V-Motor', 4806, 500, 'Allrad', 'Benzin', 6.5, 5, 4, true, 25, 'Grau', 'Leder', false, false, 369, 569, 769, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (55, 4, 12, 'Macan', 'Turbo', 'Manuell', 2016, 1970, 360, '6-Zylinder V-Motor', 2997, 500, 'Allrad', 'Benzin', 5.20000000000000018, 5, 4, true, 21, 'Blau', 'Leder', false, false, 359, 559, 759, NULL, false);
INSERT INTO public.ec_product_type (id, category_id, brand_id, model, variant, gearing_type, age, weight_kg, power_ps, engine, cubic_capacity_cm3, nm, drive_system, fuel_type, zero_to_hundred, seats, doors, add_driver, minimum_age, body_color, upholstery, massage_seats, displays_rear, price_200, price_500, price_1000, blocked_when, highlighted) OVERRIDING SYSTEM VALUE VALUES (56, 4, 2, 'Urus', '', 'Automatik', 2019, 2200, 650, '8-Zylinder V-Motor', 3996, 850, 'Allrad', 'Benzin', 3.60000000000000009, 5, 5, true, 21, 'Gelb', 'Leder', false, false, 999, 1199, 1399, NULL, true);


--
-- TOC entry 2868 (class 0 OID 34459)
-- Dependencies: 204
-- Data for Name: ec_search_words; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2870 (class 0 OID 34467)
-- Dependencies: 206
-- Data for Name: ec_user; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2877 (class 0 OID 0)
-- Dependencies: 197
-- Name: ec_brand_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ec_brand_id_seq', 16, true);


--
-- TOC entry 2878 (class 0 OID 0)
-- Dependencies: 199
-- Name: ec_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ec_category_id_seq', 5, false);


--
-- TOC entry 2879 (class 0 OID 0)
-- Dependencies: 201
-- Name: ec_pictures_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ec_pictures_id_seq', 71, true);


--
-- TOC entry 2880 (class 0 OID 0)
-- Dependencies: 203
-- Name: ec_product_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ec_product_type_id_seq', 57, false);


--
-- TOC entry 2881 (class 0 OID 0)
-- Dependencies: 205
-- Name: ec_search_words_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ec_search_words_id_seq', 1, false);


--
-- TOC entry 2882 (class 0 OID 0)
-- Dependencies: 207
-- Name: ec_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ec_user_id_seq', 1, false);


--
-- TOC entry 2724 (class 2606 OID 34476)
-- Name: ec_category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ec_category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 2722 (class 2606 OID 34478)
-- Name: ec_brand ec_brand_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ec_brand
    ADD CONSTRAINT ec_brand_pkey PRIMARY KEY (id);


--
-- TOC entry 2726 (class 2606 OID 34480)
-- Name: ec_pictures ec_pictures_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ec_pictures
    ADD CONSTRAINT ec_pictures_pkey PRIMARY KEY (id);


--
-- TOC entry 2732 (class 2606 OID 34510)
-- Name: ec_user email_uk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ec_user
    ADD CONSTRAINT email_uk UNIQUE (e_mail);


--
-- TOC entry 2728 (class 2606 OID 34482)
-- Name: ec_product_type product_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ec_product_type
    ADD CONSTRAINT product_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2730 (class 2606 OID 34484)
-- Name: ec_search_words search_words_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ec_search_words
    ADD CONSTRAINT search_words_pkey PRIMARY KEY (id);


--
-- TOC entry 2734 (class 2606 OID 34486)
-- Name: ec_user usert_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ec_user
    ADD CONSTRAINT usert_pkey PRIMARY KEY (id);


--
-- TOC entry 2736 (class 2606 OID 34487)
-- Name: ec_product_type product_brand_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ec_product_type
    ADD CONSTRAINT product_brand_fk FOREIGN KEY (brand_id) REFERENCES public.ec_brand(id);


--
-- TOC entry 2737 (class 2606 OID 34492)
-- Name: ec_product_type product_category_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ec_product_type
    ADD CONSTRAINT product_category_fk FOREIGN KEY (category_id) REFERENCES public.ec_category(id);


--
-- TOC entry 2735 (class 2606 OID 34497)
-- Name: ec_pictures product_pictures_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ec_pictures
    ADD CONSTRAINT product_pictures_fk FOREIGN KEY (product_id) REFERENCES public.ec_product_type(id);


--
-- TOC entry 2738 (class 2606 OID 34502)
-- Name: ec_search_words search_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ec_search_words
    ADD CONSTRAINT search_user_fk FOREIGN KEY (user_id) REFERENCES public.ec_user(id);


-- Completed on 2019-09-20 17:17:14

--
-- PostgreSQL database dump complete
--

