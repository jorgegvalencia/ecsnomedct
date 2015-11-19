SELECT concept.cui AS CUI, ec_concepts.concept_sctid AS SCTID, COUNT(ec_concepts.eligibility_criteria_id) AS FRECUENCY, concept.name AS CONCEPT
FROM ec_concepts, concept WHERE 
concept.sctid = ec_concepts.concept_sctid 
GROUP BY concept_sctid ORDER BY FRECUENCY DESC
LIMIT 0,100;