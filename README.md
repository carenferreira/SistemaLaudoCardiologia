# Sistema de Laudos de Cardiologia
## Escopo do projeto:
Um hospital universitário deseja informatizar seu processo e solicitação de exames e emissão de
laudos para o serviço de cardiologia.
O sistema é composto por três tipos de usuário que devem ter acesso as seguintes funcionalidades:
* Médico-residente: Realização de exame; Gerar Laudo
* Médico-docente: Avaliação dos Laudos
* Demais médicos: Solicitação de pedido de exame; Consultar Laudos e exames

Para ter acesso a aplicação é necessário realizar o login.

### Especificações das funcionalidades:
1. Solicitação de Pedido de Exame:
   O médico deve buscar o paciente e selecionar um exame por pedido, caso o paciente já tenha um exame solicitado com status de "Aguardando Exame", não será possível agendar outro. Depois de preencher todos os campos, o sistema salva a solicitação e envia o pedido de exame para o email do paciente.

   Obs: Para testar essa função é necessário ter Pacientes pré-cadastrados no banco de dados. Também será necessário alterar as informações em EmailInfoExemplo, java, dentro do pacote modelo
2. Realização do Exame:
   Ao fim da realização do exame será gerado um PDF com o resultado do exame que será salvo em uma pasta pré-definida. O status do exame é alterado para "Aguardando Laudo"

   Obs: Para testar essa funcionalidade é necessário editar o arquivo PdfCreatorExemplo.java, dentro do pacote modelo.
3. Gerar Laudo de Exames Realizados:
   O médico residente deve interpretar o PDF gerado na realização do exame e emitir o laudo e o status do exame é alterado para "Laudo realizado".

5. Avaliação dos Laudos:
   O médico docente avalia o laudo gerado pelo residente e então o status do laudo passa a ser definitivo.
6. Consultar laudos e exames:
   Médicos podem consultar os exames dos pacientes desde que estejam com laudos definitivos.
   
8. Exames cancelados:
   Após 3 dias, os exames não realizados terão seus pedidos cancelados. Um email será enviado ao paciente informando o cancelamento e o status do exame passa a ser "Exame cancelado".

## Informações Técnicas:
* Desenvolvido usando Eclipse EE 2023
* JavaSE-17
* Apache Tomcat v10.0
* Maven 4.0.0
  * Postgresql 42.2.6
  * Commons-email 2.0.0
  * Itextpdf 5.5.13.3
  * Jakarta servelet jsp jstl / api 2.0.0
## Execução:
- Baixar o projeto
- Criar o banco de dados com o script laudos.sql e alterar o arquivo ConexaoExemplo.java no pacote bd com as informações do banco criado.
- Inserir os usuários para testar o projeto
