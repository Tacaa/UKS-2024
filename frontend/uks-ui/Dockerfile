# Build stage
FROM node:18 as build
WORKDIR /app

COPY package.json package-lock.json ./
RUN npm install

COPY . .
RUN npm run build --prod

# Serve with Nginx
FROM nginx:alpine

COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist/uks-ui /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
