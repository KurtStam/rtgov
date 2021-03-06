drop table RTGOV_ACTIVITIES if exists;
drop table RTGOV_ACTIVITY_CONTEXT if exists;
drop table RTGOV_ACTIVITY_PROPERTIES if exists;
drop table RTGOV_ACTIVITY_UNITS if exists;

create table RTGOV_ACTIVITIES (type varchar(31) not null, unitId varchar(255) not null, unitIndex integer not null, principal varchar(255), timestamp bigint not null, customType varchar(255), level integer, message varchar(255), status integer, instanceId varchar(255), processType varchar(255), version varchar(255), variableName varchar(255), variableType varchar(255), variableValue varchar(255), content clob, messageType varchar(255), destination varchar(255), fault varchar(255), interface varchar(255), operation varchar(255), serviceType varchar(255), replyToId varchar(255), primary key (unitId, unitIndex));

create table RTGOV_ACTIVITY_CONTEXT (unitId varchar(255) not null, unitIndex integer not null, timeframe bigint, type varchar(255), value varchar(255));

create table RTGOV_ACTIVITY_PROPERTIES (unitId varchar(255) not null, unitIndex integer not null, value varchar(255), name varchar(255) not null, primary key (unitId, unitIndex, name));

create table RTGOV_ACTIVITY_UNITS (id varchar(255) not null, host varchar(255), node varchar(255), principal varchar(255), thread varchar(255), primary key (id));

alter table RTGOV_ACTIVITIES add constraint FK693AE720E3DE5559 foreign key (unitId) references RTGOV_ACTIVITY_UNITS;

alter table RTGOV_ACTIVITY_CONTEXT add constraint FK7FFC19525A6A791D foreign key (unitId, unitIndex) references RTGOV_ACTIVITIES;

alter table RTGOV_ACTIVITY_PROPERTIES add constraint FK728366909BA1A17D foreign key (unitId, unitIndex) references RTGOV_ACTIVITIES;

