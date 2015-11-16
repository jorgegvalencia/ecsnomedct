# Retrieve all the eligibility criteria with its associated concepts
SELECT utterance AS UTT -- ,concept.sctid AS SCTID, name AS TERM 
FROM ec_concepts, eligibility_criteria, concept WHERE 
ec_concepts.eligibility_criteria_id = eligibility_criteria.id AND ec_concepts.clinical_trial_id = eligibility_criteria.clinical_trial_id AND
ec_concepts.concept_sctid = concept.sctid 
AND concept.sctid = "224130005";