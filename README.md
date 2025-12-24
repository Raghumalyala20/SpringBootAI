# SpringBootAI

A simple Spring Boot application that demonstrates **AI integration in an enterprise-style backend service**.

## Overview
This project exposes a REST API that generates a professional customer support email using an LLM.  
It is intended to demonstrate how GenAI can be safely and cleanly integrated into a Spring Boot application.

## What it does
- Accepts **customer name**, **order ID**, and **situation** as input
- Calls a Large Language Model (Groq) to generate a professional support email
- Returns a concise, empathetic email response

## Tech Stack
- Java 17
- Spring Boot
- REST APIs
- Groq LLM (OpenAI-compatible API)
- Deployed on Railway

## How it works
1. Client sends request with customer details
2. Spring Boot service builds a structured prompt
3. Request is sent to the LLM
4. Generated email text is returned to the client

## Deployment
The application is deployed as a Spring Boot service on **Railway**.  
Configuration such as API keys and model selection is handled using environment variables.

## Status
Work in progress / learning project.

