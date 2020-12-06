insert into cozinha (nome) values('Tailandesa');
insert into cozinha (nome) values('Indiana');


insert into forma_pagamento (descricao) values('Cartão de crédito');
insert into forma_pagamento (descricao) values('Cartão de débito');
insert into forma_pagamento (descricao) values('Dinheiro');

insert into estado (nome) values('Ceará');

insert into cidade (nome, estado_id) values('Fortaleza', 1);

insert into restaurante(nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro) values('Ponto do caranguejo', 10, 1, 1, utc_timestamp, utc_timestamp, '60.710-705', 'rua dos coelhos', '115', 'B','Maraponga');

insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values('Bar do Chico', 10, 2 ,utc_timestamp, utc_timestamp);

insert into restaurante_pagamento (restaurante_id, pagamento_id) values(1, 1); 
insert into restaurante_pagamento (restaurante_id, pagamento_id) values(1, 3); 
insert into restaurante_pagamento (restaurante_id, pagamento_id) values(2, 1); 
insert into restaurante_pagamento (restaurante_id, pagamento_id) values(2, 2); 

