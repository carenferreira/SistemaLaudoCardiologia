--
-- PostgreSQL database dump
--

-- Dumped from database version 13.8
-- Dumped by pg_dump version 13.8

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
-- Name: seq-exames; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."seq-exames"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1000
    CACHE 1;


ALTER TABLE public."seq-exames" OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: exame; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exame (
    id_exame bigint DEFAULT nextval('public."seq-exames"'::regclass) NOT NULL,
    paciente character varying(30) NOT NULL,
    medico character varying(30),
    exame character varying(30),
    hipotese character varying(60),
    recomendacao character varying(120),
    situacao character varying(30),
    data date,
    hora time without time zone,
    datarealizacao date,
    medicoexame character varying(30)
);


ALTER TABLE public.exame OWNER TO postgres;

--
-- Name: laudo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.laudo (
    id bigint NOT NULL,
    medico character varying NOT NULL,
    laudo character varying,
    diagnostico character varying,
    situacao character varying
);


ALTER TABLE public.laudo OWNER TO postgres;

--
-- Name: medico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medico (
    crm character varying(30) NOT NULL,
    nome character varying(60) NOT NULL
);


ALTER TABLE public.medico OWNER TO postgres;

--
-- Name: medicodocente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medicodocente (
    crm character varying(30) NOT NULL,
    nome character varying(60) NOT NULL,
    titulacao character varying(30) NOT NULL
);


ALTER TABLE public.medicodocente OWNER TO postgres;

--
-- Name: medicoresidente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medicoresidente (
    crm character varying(30) NOT NULL,
    nome character varying(60) NOT NULL,
    matricula character varying(60) NOT NULL
);


ALTER TABLE public.medicoresidente OWNER TO postgres;

--
-- Name: paciente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.paciente (
    cpf character varying(30) NOT NULL,
    nome character varying(60) NOT NULL,
    email character varying(60),
    sexo character varying(30),
    idade bigint,
    datanascimento date
);


ALTER TABLE public.paciente OWNER TO postgres;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    login character varying(30) NOT NULL,
    senha character varying(30) NOT NULL,
    tipo bigint NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- Name: exame exame_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exame
    ADD CONSTRAINT exame_pkey PRIMARY KEY (id_exame);


--
-- Name: laudo laudo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.laudo
    ADD CONSTRAINT laudo_pkey PRIMARY KEY (id);


--
-- Name: medicodocente medicoDocente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medicodocente
    ADD CONSTRAINT "medicoDocente_pkey" PRIMARY KEY (crm);


--
-- Name: medicoresidente medicoResidente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medicoresidente
    ADD CONSTRAINT "medicoResidente_pkey" PRIMARY KEY (crm);


--
-- Name: medico medico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medico
    ADD CONSTRAINT medico_pkey PRIMARY KEY (crm);


--
-- Name: paciente paciente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT paciente_pkey PRIMARY KEY (cpf);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (login);


--
-- Name: fki_id_exame; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_id_exame ON public.laudo USING btree (id);


--
-- Name: laudo id_exame; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.laudo
    ADD CONSTRAINT id_exame FOREIGN KEY (id) REFERENCES public.exame(id_exame) NOT VALID;


--
-- Name: exame medico; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exame
    ADD CONSTRAINT medico FOREIGN KEY (medico) REFERENCES public.medico(crm);


--
-- Name: exame paciente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exame
    ADD CONSTRAINT paciente FOREIGN KEY (paciente) REFERENCES public.paciente(cpf);


--
-- PostgreSQL database dump complete
--

