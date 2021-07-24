ALTER TABLE discount ADD UNIQUE (name);
ALTER TABLE tag ADD UNIQUE (tagName);
ALTER TABLE country ADD UNIQUE (name);
ALTER TABLE city ADD UNIQUE (name);
ALTER TABLE category ADD UNIQUE (name);
ALTER TABLE address ADD UNIQUE (address);