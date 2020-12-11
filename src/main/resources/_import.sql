insert into cozinha (nome) values('Tailandesa');
insert into cozinha (nome) values('Indiana');


insert into forma_pagamento (descricao) values('Cartão de crédito');
insert into forma_pagamento (descricao) values('Cartão de débito');
insert into forma_pagamento (descricao) values('Dinheiro');

insert into estado (nome) values('Ceará');

insert into cidade (nome, estado_id) values('Fortaleza', 1);

insert into restaurante(nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro) values('Ponto do caranguejo', 10, 1, 1, utc_timestamp, utc_timestamp, '60.710-705', 'rua dos coelhos', '115', 'B','Maraponga');

insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values('Bar do Chico', 10, 2 ,utc_timestamp, utc_timestamp);
insert into produto(nome, descricao, preco, ativo, restaurante_id) values("Camarão Grande", "super prato para 2 pessoas", 88.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 0, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 0, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 2);

insert into restaurante_pagamento (restaurante_id, pagamento_id) values(1, 1); 
insert into restaurante_pagamento (restaurante_id, pagamento_id) values(1, 3); 
insert into restaurante_pagamento (restaurante_id, pagamento_id) values(2, 1); 
insert into restaurante_pagamento (restaurante_id, pagamento_id) values(2, 2); 

