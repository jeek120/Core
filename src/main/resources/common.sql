
alter table tab1 add `status` char(1) DEFAULT NULL;
alter table tab1 add `operatTime` datetime DEFAULT NULL;
alter table tab1 add `creatTime` datetime DEFAULT NULL;
alter table tab1 add `version` int(11) DEFAULT NULL;
alter table tab1 add `creatIp` char(16) DEFAULT NULL;
alter table tab1 add `operatIp` char(16) DEFAULT NULL;
alter table tab1 add `creator` bigint(20) DEFAULT NULL;
alter table tab1 add `operator` bigint(20) DEFAULT NULL;
