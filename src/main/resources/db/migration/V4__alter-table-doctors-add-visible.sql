ALTER TABLE doctors ADD COLUMN active tinyint;
UPDATE doctors SET active = 1;