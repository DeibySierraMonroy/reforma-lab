FROM eclipse-temurin:21-jdk-alpine

# Crear el grupo y usuario

# Crear el grupo y usuario
RUN addgroup -S devopsc && adduser -S javams -G devopsc

# Crear y asignar permisos al directorio
RUN mkdir -p /opt/app/config && chmod -R 755 /opt/app/config

# Copiar los archivos
COPY build/libs/ms-solicitud-reforma.jar /opt/app/app.jar

# Cambiar al usuario sin privilegios
USER javams:devopsc

# Definir variables de entorno y volumen
ENV JAVA_OPTS=""
VOLUME /tmp

# Exponer el puerto
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /opt/app/app.jar " ]