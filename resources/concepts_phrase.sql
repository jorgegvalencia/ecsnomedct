# Concepts by phrase
SELECT clinical_trial_id, concept_sctid, phrase, name FROM concept, ec_concepts WHERE 
phrase = "metastatic kidney cancer" AND
concept_sctid = concept.sctid;