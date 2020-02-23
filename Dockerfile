FROM payara/micro:5.193

COPY target/payara-rest-micro-1.0-SNAPSHOT.war $DEPLOY_DIR

CMD ["--deploymentDir", "/opt/payara/deployments", "--contextroot", "rest-micro"]