USE norm;
SELECT eligibility_criteria.clinical_trial_id AS NCT, utterance AS UTT,inc_exc AS IE,sctid AS SCTID, name AS TERM, semantic_type AS TYPE
FROM ec_concepts, eligibility_criteria, concept WHERE 
ec_concepts.concept_sctid = concept.sctid AND
ec_concepts.eligibility_criteria_id = eligibility_criteria.id AND ec_concepts.clinical_trial_id = eligibility_criteria.clinical_trial_id;