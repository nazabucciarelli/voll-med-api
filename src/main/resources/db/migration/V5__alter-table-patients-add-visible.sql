ALTER TABLE patients ADD COLUMN active tinyint;
UPDATE patients SET active = 1;