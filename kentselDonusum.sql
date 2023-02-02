--
-- PostgreSQL database dump
--

-- Dumped from database version 13.4
-- Dumped by pg_dump version 13.4

-- Started on 2023-01-18 10:43:33

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

--
-- TOC entry 208 (class 1255 OID 34367)
-- Name: control_fonk(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.control_fonk() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
id integer;
BEGIN
select konutsahibi_id  into id from evler group by konut_sahibi_id having count(*) >= 2;
if(new.konut_sahibi_id = id ) then                  
       
RETURN null;
else 
     RETURN new;
end if;
   
END;
$$;


ALTER FUNCTION public.control_fonk() OWNER TO postgres;

--
-- TOC entry 221 (class 1255 OID 34379)
-- Name: evsayisi(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.evsayisi() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare 
number integer;
begin  
	select count(*) into number from evler 
	where konut_sahibi_id = new.konut_sahibi_id;
   if (number >= 2) then
  
   return old;
   else
   return new;
   
   end if;
   end

$$;


ALTER FUNCTION public.evsayisi() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 201 (class 1259 OID 34211)
-- Name: evler; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.evler (
    id integer NOT NULL,
    apartman_ismi character varying,
    apartman_no integer,
    adres character varying,
    konut_sahibi_id integer
);


ALTER TABLE public.evler OWNER TO postgres;

--
-- TOC entry 209 (class 1255 OID 34371)
-- Name: firmayikim(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.firmayikim(firmaid integer) RETURNS public.evler[]
    LANGUAGE plpgsql
    AS $$
Declare 
apt_cursor cursor for 
Select e.apartman_ismi
From firmalar as f, evler as e, yikim as y
Where firmaid=y.firma_id and y.ev_id = e.id ;

ev evler[];

i integer;
Begin

i := 1;
For apt_record in apt_cursor loop
	ev[i] = apt_record;
	i := i+1;
End loop;
Return ev;
End;
$$;


ALTER FUNCTION public.firmayikim(firmaid integer) OWNER TO postgres;

--
-- TOC entry 223 (class 1255 OID 34383)
-- Name: silmekontrol(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.silmekontrol() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare 
	kontrol integer;
begin 
	kontrol = 0;
	select id into kontrol from evler where konut_sahibi_id = old.id;
	if(kontrol = 0 ) then 
      	delete from konut_sahibi where id=old.id;
	return null;
	else  
      return old;
end if;
 end;
$$;


ALTER FUNCTION public.silmekontrol() OWNER TO postgres;

--
-- TOC entry 222 (class 1255 OID 34375)
-- Name: yikimodemesi(integer, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.yikimodemesi(evid integer, tutar integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$
	declare odenmesiGereken integer;
begin 
    select maliyet into odenmesiGereken from yikim where ev_id=evId;
	
	if tutar = odenmesiGereken then 
	  update yikim set odeme_bilgisi=true where ev_id=evId;
	  return 1;
	 else
	 return 0;
	 end if;
	end; 
	$$;


ALTER FUNCTION public.yikimodemesi(evid integer, tutar integer) OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 34209)
-- Name: evler_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.evler ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.evler_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 203 (class 1259 OID 34221)
-- Name: firmalar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.firmalar (
    id integer NOT NULL,
    firma_adi character varying,
    tel_no character varying(11),
    email character varying,
    adres character varying
);


ALTER TABLE public.firmalar OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 34219)
-- Name: firmalar_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.firmalar ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.firmalar_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 204 (class 1259 OID 34229)
-- Name: konut_sahibi; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.konut_sahibi (
    id integer NOT NULL,
    isim character varying,
    soyisim character varying,
    tel_no character varying(11)
);


ALTER TABLE public.konut_sahibi OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 34283)
-- Name: konut_sahibi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.konut_sahibi ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.konut_sahibi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 205 (class 1259 OID 34242)
-- Name: yikim; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.yikim (
    id integer NOT NULL,
    ev_id integer,
    firma_id integer,
    yikim_zamani date,
    maliyet integer,
    odeme_bilgisi boolean
);


ALTER TABLE public.yikim OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 34290)
-- Name: yikim_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.yikim ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.yikim_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3020 (class 0 OID 34211)
-- Dependencies: 201
-- Data for Name: evler; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.evler (id, apartman_ismi, apartman_no, adres, konut_sahibi_id) FROM stdin;
3	Çiçek Apartman	8	Esenler/İstanbul	2
4	Yıldız Apartman 	12	Cevizlibağ/İstanbul	3
7	Mavi Apartman	8	Zeytinburnu/İstanbul	1
8	Pembe Apartman	8	Zeytinburnu/İstanbul	1
18	Kelebek Apartman	12	Levent/İstanbul	2
20	Menekşe Apartman	10	Esenler/İstanbul 	3
22	Kırmızı Apartman 	18	Fatih/İstanbul	4
23	Sarı Apartman	18	Levent/İstanbul	8
24	Çılgın Apartman	10	Güngören/İstanbul	7
25	Huzur Apartman	18	Levent/İstanbul	5
27	Çengel Apartman	18	Levent/İstanbul	7
28	Asude Apartman	18	Levent/İstanbul	4
29	Mor Apartman	18	Levent/İstanbul	5
30	Bahar Apartman	18	Levent/İstanbul	12
31	Kış Apartman	18	Levent/İstanbul	11
32	Sakin Apartman	18	Levent/İstanbul	9
33	Sarı Apartman	18	Levent/İstanbul	8
40	Bahar Apartman	18	Levent/İstanbul	12
41	Kış Apartman	18	Levent/İstanbul	11
42	Sakin Apartman	18	Levent/İstanbul	9
\.


--
-- TOC entry 3022 (class 0 OID 34221)
-- Dependencies: 203
-- Data for Name: firmalar; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.firmalar (id, firma_adi, tel_no, email, adres) FROM stdin;
1	Esyol Yapı	1123452133	esyol@hotmail.com	Esenler/İstanbul
2	Esyol Yapı	1123452133	esyol@hotmail.com	Esenler/İstanbul
3	Çelik Yapı	112255663	celik@gmail.com	Levent_istanbul
4	Uygur Yapı	4562255663	uygur@gmail.com	Güngören/İstanbul
5	Yelmer Yapı	4562255663	yelmer@hotmail.com	Cevizlibağ/İstanbul
6	Sevgi Yapı	4456255663	sevgi@gmail.com	Esenler/İstanbul
7	Utluk Yapı	14762255663	utluk@gmail.com	Sarıyer/İstanbul
8	Demir Yapı	4562247633	demir@gmail.com	Maslak/İstanbul
9	Aktaş Yapı	4562225783	aktas@gmail.com	Zeytinburnu/İstanbul
10	Saraç Yapı	2566855663	sarac@otmail.com	Güngören/İstanbul
11	Aslan Yapı	4562247856	aslan@gmail.com	Esenler/İstanbul
12	Turgut Yapı	4561450288	turgut@gmail.com	Cevizlibağ/İstanbul
13	Çakır Yapı	1425655663	cakir@gmail.com	Zeytinburnu/İstanbul
14	Tuncay Yapı	4561478965	tuncay@hotmail.com	Fatih/İstanbul
\.


--
-- TOC entry 3023 (class 0 OID 34229)
-- Dependencies: 204
-- Data for Name: konut_sahibi; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.konut_sahibi (id, isim, soyisim, tel_no) FROM stdin;
1	Betül	Çelik	05555555555
2	Zeynep	Acar	01234567891
3	Nilay	Özkan	12345678910
20	Diyar	Kara	1247563258
12	Diyar	Kara	5556568920
11	Fatma	Akın	5466568920
19	Fatma	Akın	5436568920
18	Berat	Dearala	51468965920
4	Mehmet 	Kıymazaslan	52468965920
5	Elanur	Yelmer	7858965920
6	Zeynep	Utlu	5258965920
7	Erdem	Boztaş	5257965920
8	Ayşe	Ay	5257965780
9	Zaide	Alp	5123965780
10	Berat	Dearala	5165874480
14	Zeynep	Utlu	54689874480
15	Erdem	Boztaş	54589874480
13	Elanur	Yelmer	55689874480
17	Zaide	Alp	58989874480
16	Ayşe	Ay	58989874480
\.


--
-- TOC entry 3024 (class 0 OID 34242)
-- Dependencies: 205
-- Data for Name: yikim; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.yikim (id, ev_id, firma_id, yikim_zamani, maliyet, odeme_bilgisi) FROM stdin;
1	3	1	2023-08-03	125000	t
8	8	5	2023-08-05	140000	f
11	18	7	2024-05-06	150000	f
16	33	10	2025-05-03	120000	f
17	31	10	2023-04-07	98000	f
2	4	1	2023-05-04	13000	f
14	27	4	2024-05-08	13000	f
15	41	9	2024-05-03	12800	f
19	28	5	2024-02-07	12100	f
13	22	7	2024-05-08	190000	f
18	29	6	2023-02-07	190000	f
\.


--
-- TOC entry 3032 (class 0 OID 0)
-- Dependencies: 200
-- Name: evler_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.evler_id_seq', 42, true);


--
-- TOC entry 3033 (class 0 OID 0)
-- Dependencies: 202
-- Name: firmalar_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.firmalar_id_seq', 14, true);


--
-- TOC entry 3034 (class 0 OID 0)
-- Dependencies: 206
-- Name: konut_sahibi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.konut_sahibi_id_seq', 20, true);


--
-- TOC entry 3035 (class 0 OID 0)
-- Dependencies: 207
-- Name: yikim_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.yikim_id_seq', 19, true);


--
-- TOC entry 2877 (class 2606 OID 34215)
-- Name: evler evler_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evler
    ADD CONSTRAINT evler_pkey PRIMARY KEY (id);


--
-- TOC entry 2879 (class 2606 OID 34228)
-- Name: firmalar firmalar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.firmalar
    ADD CONSTRAINT firmalar_pkey PRIMARY KEY (id);


--
-- TOC entry 2881 (class 2606 OID 34236)
-- Name: konut_sahibi konut_sahibi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.konut_sahibi
    ADD CONSTRAINT konut_sahibi_pkey PRIMARY KEY (id);


--
-- TOC entry 2883 (class 2606 OID 34246)
-- Name: yikim yikim_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.yikim
    ADD CONSTRAINT yikim_pkey PRIMARY KEY (id);


--
-- TOC entry 2887 (class 2620 OID 34380)
-- Name: evler evsayisikontrol; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER evsayisikontrol BEFORE INSERT ON public.evler FOR EACH ROW EXECUTE FUNCTION public.evsayisi();


--
-- TOC entry 2888 (class 2620 OID 34384)
-- Name: konut_sahibi silmekisiti; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER silmekisiti BEFORE DELETE ON public.konut_sahibi FOR EACH ROW EXECUTE FUNCTION public.silmekontrol();


--
-- TOC entry 2884 (class 2606 OID 34285)
-- Name: evler fktl99yax6jjn4k1y4u8ylt1gqw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evler
    ADD CONSTRAINT fktl99yax6jjn4k1y4u8ylt1gqw FOREIGN KEY (konut_sahibi_id) REFERENCES public.konut_sahibi(id);


--
-- TOC entry 2885 (class 2606 OID 34247)
-- Name: yikim yikim_ev_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.yikim
    ADD CONSTRAINT yikim_ev_id_fkey FOREIGN KEY (ev_id) REFERENCES public.evler(id);


--
-- TOC entry 2886 (class 2606 OID 34252)
-- Name: yikim yikim_firma_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.yikim
    ADD CONSTRAINT yikim_firma_id_fkey FOREIGN KEY (firma_id) REFERENCES public.firmalar(id);


-- Completed on 2023-01-18 10:43:33

--
-- PostgreSQL database dump complete
--

