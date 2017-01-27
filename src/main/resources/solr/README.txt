

#stop command
bin/solr stop -all

#help
bin/solr -help
#command help
bin/solr <command> -help
#e.g bin/solr start -help

#creating a new core.
#choose a solo home directory . Keep an empty sold.xml file there
#run the following command
bin/solr start -s /Users/a.nigam/Documents/workspace/smartbookmark/src/main/resources/solr
#now create the core using following command
bin/solr create -c smart bookmark