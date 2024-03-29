#!/bin/bash

dir="./certs"

if [ ! -d "${dir}" ]; then
	mkdir "${dir}"
else
	echo "cleaning dir.."
	rmDir="${dir}/*" # TODO figure out one-liner
	rm $rmDir
fi

openssl req -x509 -out certs/nginx-cert.crt -keyout certs/nginx-cert.key \
  -newkey rsa:2048 -nodes -sha256 \
  -subj '/CN=localhost' -extensions EXT -config <( \
   printf "[dn]\nCN=localhost\n[req]\ndistinguished_name = dn\n[EXT]\nsubjectAltName=DNS:localhost\nkeyUsage=digitalSignature\nextendedKeyUsage=serverAuth")