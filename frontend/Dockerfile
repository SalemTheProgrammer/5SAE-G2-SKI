# Stage 1: Build the Angular application
FROM node:20 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to install dependencies
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the Angular project files into the container
COPY . .

# Build the Angular project for production
RUN npm run build -- --configuration production

# Stage 2: Serve the Angular application with Apache
FROM httpd:alpine

# Copy the built Angular files from the previous stage to Apache's HTML directory
COPY --from=build /app/dist /usr/local/apache2/htdocs/

# Expose port 80 to the host
EXPOSE 80

# Start Apache
CMD ["httpd-foreground"]
