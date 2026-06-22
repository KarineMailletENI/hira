# Hira 🕊️

Hira est une application web de planification et de gestion de voyage. Elle permet aux utilisateurs de centraliser l'organisation de leurs séjours, de la découverte d'activités à la gestion rigoureuse de leur budget, en passant par le stockage sécurisé de leurs documents administratifs.

---

## Fonctionnalités principales

- **Gestion des utilisateurs** : inscription, connexion et espace personnel sécurisé.
- **Boîte à idées** : recherche d'activités et ajout manuel d'inspirations (TikTok, Instagram, etc.).
- **Coffre-fort numérique** : stockage des documents essentiels (passeport, billets, assurances).
- **Suivi budgétaire** : estimation des coûts et visualisation claire des dépenses par catégorie.

---

## Stack technique

| Couche      | Technologie                  |
|-------------|------------------------------|
| Backend     | Java 21 / Spring Boot / Maven |
| Frontend    | Vue.js 3 / Vite              |
| Base de données | PostgreSQL ou MySQL *(à définir)* |

---

## Structure du projet

hira/

├── backend/    ← API REST Spring Boot 

└── frontend/   ← Interface Vue.js

---

## Installation et lancement

### Prérequis

- Java 21 ou supérieur
- Node.js 22 ou supérieur / npm 10 ou supérieur
- Un SGBD (PostgreSQL ou MySQL)

### Backend (Spring Boot)

1. Se placer dans le dossier : `cd backend`
2. Configurer la base de données dans `src/main/resources/application.properties`
3. Lancer depuis IntelliJ

### Frontend (Vue.js)

1. Se placer dans le dossier : `cd frontend`
2. Installer les dépendances :

```bash
npm install
```

3. Lancer le serveur de développement :

```bash
npm run dev
```

L'application est accessible sur `http://localhost:5173/`

Autres commandes utiles :

```bash
npm run build    # Compiler pour la production
npm run format   # Formater le code (Prettier)
npm run lint     # Analyser le code (ESLint)
```

---

Projet développé dans un cadre d'apprentissage et de développement personnel.