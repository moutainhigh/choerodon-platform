package script.db

databaseChangeLog(logicalFilePath: 'script/db/hiam_role_auth_data_line.groovy') {
    changeSet(author: "qingsheng.chen@hand-china.com", id: "2019-06-14-hiam_role_auth_data_line") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_role_auth_data_line_s', startValue:"1")
        }
        createTable(tableName: "hiam_role_auth_data_line", remarks: "角色单据权限管理行") {
            column(name: "auth_data_line_id", type: "bigint", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "auth_data_id", type: "bigint",  remarks: "权限ID，hiam_role_auth_data.auth_data_id")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint",  remarks: "租户ID，HPFM.HPFM_TENANT")  {constraints(nullable:"false")}  
            column(name: "data_id", type: "bigint",  remarks: "数据ID")  {constraints(nullable:"false")}  
            column(name: "data_code", type: "varchar(" + 80 * weight + ")",  remarks: "数据代码/编码")   
            column(name: "data_name", type: "varchar(" + 360 * weight + ")",  remarks: "数据名称")
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"auth_data_id,data_id",tableName:"hiam_role_auth_data_line",constraintName: "hiam_role_auth_data_line_u1")
    }
	
	changeSet(author: 'jiangzhou.bo@hand-china.com', id: '2020-03-16-hiam_role_auth_data_line') {
        addColumn(tableName: 'hiam_role_auth_data_line') {
            column(name: 'data_source',  type: 'varchar(30)', defaultValue: "DEFAULT", remarks: '数据来源') {constraints(nullable: "false")}
        }
    }
}