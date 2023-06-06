INSERT INTO CATEGORIES(category_id, NAME) VALUES
	(1, 'TERROR'), 
	(2, 'COMEDIA'), 
	(3, 'ACCIÓN');
    
INSERT INTO TV_SHOWS(tv_show_id, NAME, SHORT_DESC, LONG_DESC, YEAR, RECOMMENDED_AGE, category_id) VALUES
	(1, 'Juego de tronos', 'Descripción corta', 'Descripción larga', '2012', 16, 3), 
	(2, 'American horror Story', NULL, NULL, '2010', 16, 1), 
	(3, 'Big Bang', NULL, NULL, '2008', 7, 2),
	(4, 'SERIE DE PRUEBA', NULL, NULL, '2008', 18, 1);
    
INSERT INTO SEASONS(SEASON_ID, NUMBER, NAME, tv_show_id) VALUES
	(1, 1, 'One', 1), 
	(2, 2, 'Two', 1), 
	(3, 1, 'One', 2), 
	(4, 2, 'Two', 2), 
	(5, 3, 'Three', 2), 
	(7, 1, 'One', 4),
	(8, 2, 'Dos', 4),
	(9, 3, 'Tres', 4);

INSERT INTO CHAPTERS(chapter_ID, NUMBER, NAME, DURATION, SEASON_ID) VALUES
	(1, 1, 'Chapter 1', 43, 1), 
	(2, 2, 'Chapter 2', 45, 1), 
	(3, 3, 'Chapter 3', 44, 1),
	(4, 1, 'Chapter 0', 50, 2),
	(5, 1, 'Chapter 1', 50, 7),
	(6, 2, 'Chapter 2', 50, 7),
	(7, 1, 'Chapter 1', 50, 9);