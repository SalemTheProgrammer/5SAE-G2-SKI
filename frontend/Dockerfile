# Stage 1: Build the Angular application
FROM node:20 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to install dependencies first
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the Angular project files into the container
COPY . .

# Build the Angular project for production
RUN npm run build -- --project=frontned-gestion-de-ski --configuration production

# Stage 2: Serve the Angular application with NGINX
FROM nginx:alpine

# Remove default NGINX static files
RUN rm -rf /usr/share/nginx/html/*

# Copy the built Angular files from the previous stage to NGINX's HTML directory
COPY --from=build /app/dist/frontned-gestion-de-ski /usr/share/nginx/html

# Expose port 80 to the host
EXPOSE 80

# Start NGINX
CMD ["nginx", "-g", "daemon off;"]
