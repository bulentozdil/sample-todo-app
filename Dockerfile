FROM couchbase
 
COPY configure.sh /configure.sh

RUN chmod +x /configure.sh

CMD ["/configure.sh"]