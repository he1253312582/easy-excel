select * from scenes_info;
select * from formula_info;
select * from template_info;
select * from template_formula;

#修改一张表的引擎为innoDB,这样才可以支持数据库的事务管理
alter table scenes_info engine=innoDB;
alter table formula_info engine=innoDB;
alter table template_info engine=innoDB;
alter table template_formula engine=innoDB;


#创建序列
DROP TABLE IF EXISTS sequence;
CREATE TABLE sequence (
  name VARCHAR(50) NOT NULL,
  current_value INT NOT NULL,
  increment INT NOT NULL DEFAULT 1,
  PRIMARY KEY (name)
) ENGINE=InnoDB;

#创建--取当前值的函数
DROP FUNCTION IF EXISTS currval;
DELIMITER $
    CREATE FUNCTION currval (seq_name VARCHAR(50))
        RETURNS INTEGER
        LANGUAGE SQL
        DETERMINISTIC
        CONTAINS SQL
        SQL SECURITY DEFINER
        COMMENT ''
    BEGIN
        DECLARE value INTEGER;
        SET value = 0;
        SELECT current_value INTO value
        FROM sequence
        WHERE name = seq_name;
        RETURN value;
    END
$
DELIMITER ;

#创建--更新当前值的函数
DROP FUNCTION IF EXISTS setval;
DELIMITER $
CREATE FUNCTION setval (seq_name VARCHAR(50), value INTEGER)
    RETURNS INTEGER
    LANGUAGE SQL
    DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN
    UPDATE sequence
    SET current_value = value
    WHERE name = seq_name;
    RETURN currval(seq_name);
END
$
DELIMITER ;

#添加一个sequence名称和初始值,以及自增幅度
INSERT INTO sequence VALUES ('TestSeq', 0, 1);
#设置指定sequence的初始值
SELECT SETVAL('TestSeq', 10);
#查询指定sequence的当前值
SELECT CURRVAL('TestSeq');
#查询指定sequence的下一个值
SELECT NEXTVAL('TestSeq');