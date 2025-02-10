INSERT INTO livro (id, titulo, autor, anoPublicacao, categoria, disponivel) VALUES
('1''Dom Quixote', 'Miguel de Cervantes', 1605, 'Clássico', true),
('2','1984', 'George Orwell', 1949, 'Ficção Científica', true),
('3','O Senhor dos Anéis', 'J.R.R. Tolkien', 1954, 'Fantasia', false),
('4','Cem Anos de Solidão', 'Gabriel García Márquez', 1967, 'Realismo Mágico', true),
('5','A Revolução dos Bichos', 'George Orwell', 1945, 'Sátira', true);

INSERT INTO membro (nome, email, telefone, endereco, cpf) VALUES
('João Silva', 'joao.silva@example.com', '(11) 98765-4321', 'Rua das Flores, 123', '123.456.789-01'),
('Maria Oliveira', 'maria.oliveira@example.com', '(21) 99876-5432', 'Avenida Principal, 456', '987.654.321-02'),
('Pedro Santos', 'pedro.santos@example.com', '(31) 98765-1234', 'Rua das Palmeiras, 789', '456.789.123-03'),
('Ana Costa', 'ana.costa@example.com', '(41) 99876-4321', 'Avenida Central, 101', '321.654.987-04'),
('Carlos Rocha', 'carlos.rocha@example.com', '(51) 98765-6789', 'Rua das Árvores, 202', '654.321.987-05');

INSERT INTO emprestimo (livro_id, membro_id, data_emprestimo, data_prevista_devolucao, data_devolucao ) VALUES
(2, 1, '2003-07-03', '2003-08-03', '2003-08-01');

INSERT IGNORE INTO reserva (livro_id, membro_id, data_reserva) VALUES
(1, 3, '2023-10-10'), 
(4, 1, '2023-10-12');
