# Étape 1 : Utiliser une image Node.js pour construire l'application Angular
FROM node:20 AS build

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier package.json et package-lock.json (si disponible) pour installer les dépendances
COPY package*.json ./

# Installer les dépendances
RUN npm install

# Installer Angular CLI globalement dans le conteneur
RUN npm install -g @angular/cli

# Copier le reste du code source du projet Angular
COPY . .

# Construire l'application Angular pour la production
RUN ng build --project=frontend --configuration production

# Étape 2 : Utiliser une image Apache pour servir l'application
FROM httpd:alpine

# Copier les fichiers construits dans le répertoire d'Apache
COPY --from=build /app/dist/frontend /usr/local/apache2/htdocs/

# Exposer le port 80
EXPOSE 80

# Démarrer Apache
CMD ["httpd-foreground"]
