Configuração do ambiente com Wildfly e H2

//No diretorio do Wildfly acessar a pasta bin e executar jboss-cli e inserir o comando abaixo

embed-server --server-config=standalone.xml --std-out=echo

//Criar o módulo para referenciar o driver do banco H2
module add --name=h2_desafio --resources=${user.home}/Downloads/h2-1.4.193.jar --dependencies=javax.api,javax.transaction.api 
/subsystem=datasources/jdbc-driver=h2:add(driver-name=h2_desafio,driver-module-name=com.h2database.h2, driver-xa-datasource-class-name=org.h2.jdbcx.JdbcDataSource)

//Criando o datasouce para a conexão com o banco
data-source add --name=h2_DesafioDS  --driver-name=h2 --jndi-name=java:jboss/datasources/h2_DesafioDS  --connection-url=jdbc:h2:tcp://localhost/~/test;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE --user-name=sa  --password=sa --enabled=true

Obs: No segundo comando é assumido que o driver esta presente em Downloads
