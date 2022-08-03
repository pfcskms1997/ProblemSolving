-- Programmers Lv4. 보호소에서 중성화한 동물

SELECT i.ANIMAL_ID, i.ANIMAL_TYPE, i.NAME
FROM ANIMAL_INS as i, ANIMAL_OUTS as o
WHERE i.ANIMAL_ID = o.ANIMAL_ID
AND i.SEX_UPON_INTAKE like "Intact%"
AND (o.SEX_UPON_OUTCOME like "Spayed%" OR o.SEX_UPON_OUTCOME like "Neutered%");;