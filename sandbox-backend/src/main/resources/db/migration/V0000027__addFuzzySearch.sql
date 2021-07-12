DROP function IF EXISTS `soundex_match`;

DELIMITER $$

CREATE FUNCTION `soundex_match`(
    needle varchar(128), haystack text, splitChar varchar(1)) RETURNS tinyint
    DETERMINISTIC
BEGIN
    declare spacePos int;
    declare searchLen int default 0;
    declare curWord varchar(128) default '';
    declare tempStr text default haystack;
    declare tmp text default '';
    declare soundx1 varchar(64) default '';
    declare soundx2 varchar(64) default '';

    set searchLen = length(haystack);
    set spacePos  = locate(splitChar, tempStr);
    set soundx1   = soundex(needle);

    while searchLen > 0 do
            if spacePos = 0 then
                set tmp = tempStr;
                select soundex(tmp) into soundx2;
                if soundx1 = soundx2 then
                    return 1;
                else
                    return 0;
                end if;
            else
                set tmp = substr(tempStr, 1, spacePos-1);
                set soundx2 = soundex(tmp);
                if soundx1 = soundx2 then
                    return 1;
                end if;

                set tempStr = substr(tempStr, spacePos+1);
                set searchLen = length(tempStr);
            end if;

            set spacePos = locate(splitChar, tempStr);
        end while;

    return 0;
END$$

DELIMITER ;

DROP function IF EXISTS `soundex_match_all`;

DELIMITER $$

CREATE FUNCTION `soundex_match_all`(
    needle varchar(128), haystack text, splitChar varchar(1)) RETURNS tinyint(4)
    DETERMINISTIC
BEGIN
    /* find the first instance of the splitting character */
    DECLARE comma  INT  DEFAULT 0;
    DECLARE word   TEXT;

    SET comma = LOCATE(splitChar, needle);
    SET word = TRIM(needle);

    if LENGTH(haystack) = 0 then
        return 0;
    elseif comma = 0 then
        /* one word search term */
        return soundex_match(word, haystack, splitChar);
    end if;

    SET word = trim(substr(needle, 1, comma));

    /* Insert each split variable into the word variable */
    REPEAT
        if soundex_match(word, haystack, splitChar) = 0 then
            return 0;
        end if;

        /* get the next word */
        SET needle = trim(substr(needle, comma));
        SET comma  = LOCATE(splitChar, needle);
        if comma = 0 then
            /* last word */
            return soundex_match(needle, haystack, splitChar);
        end if;

        SET word = trim(substr(needle, 1, comma));
    UNTIL length(word) = 0
        END REPEAT;

    return 0;
END$$

DELIMITER ;
