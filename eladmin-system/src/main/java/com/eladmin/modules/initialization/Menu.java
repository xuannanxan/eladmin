package com.eladmin.modules.initialization;

import com.eladmin.modules.system.service.MenuService;
import com.eladmin.jsonconfig.Config;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class Menu {
    private static final Logger log = LoggerFactory.getLogger(Manager.class);
    private final  MenuService menuService ;

    @PostConstruct
    public void InitialMenu() throws Exception {

        //如果用户不存在
        if (menuService.queryAll('',) == null) {
            log.info(Config.token().getNickName()+"不存在，开始进行初始化...");

            menuService.create();
            log.info("初始化"+Config.token().getNickName()+"成功");
        }

}
/**
 * INSERT INTO `sys_menu` VALUES (1, NULL, 7, 0, '系统管理', NULL, NULL, 1, 'system', 'system', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:11:29', NULL);
 * INSERT INTO `sys_menu` VALUES (2, 1, 3, 1, '用户管理', 'User', 'system/user/index', 2, 'peoples', 'user', b'0', b'0', b'0', 'user:list', NULL, NULL, '2018-12-18 15:14:44', NULL);
 * INSERT INTO `sys_menu` VALUES (3, 1, 3, 1, '角色管理', 'Role', 'system/role/index', 3, 'role', 'role', b'0', b'0', b'0', 'roles:list', NULL, NULL, '2018-12-18 15:16:07', NULL);
 * INSERT INTO `sys_menu` VALUES (5, 1, 3, 1, '菜单管理', 'Menu', 'system/menu/index', 5, 'menu', 'menu', b'0', b'0', b'0', 'menu:list', NULL, NULL, '2018-12-18 15:17:28', NULL);
 * INSERT INTO `sys_menu` VALUES (6, NULL, 5, 0, '系统监控', NULL, NULL, 10, 'monitor', 'monitor', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:17:48', NULL);
 * INSERT INTO `sys_menu` VALUES (7, 6, 0, 1, '操作日志', 'Log', 'monitor/log/index', 11, 'log', 'logs', b'0', b'1', b'0', NULL, NULL, 'admin', '2018-12-18 15:18:26', '2020-06-06 13:11:57');
 * INSERT INTO `sys_menu` VALUES (9, 6, 0, 1, 'SQL监控', 'Sql', 'monitor/sql/index', 18, 'sqlMonitor', 'druid', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:19:34', NULL);
 * INSERT INTO `sys_menu` VALUES (10, NULL, 5, 0, '组件管理', NULL, NULL, 50, 'zujian', 'components', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-19 13:38:16', NULL);
 * INSERT INTO `sys_menu` VALUES (11, 10, 0, 1, '图标库', 'Icons', 'components/icons/index', 51, 'icon', 'icon', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-19 13:38:49', NULL);
 * INSERT INTO `sys_menu` VALUES (14, 36, 0, 1, '邮件工具', 'Email', 'tools/email/index', 35, 'email', 'email', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-27 10:13:09', NULL);
 * INSERT INTO `sys_menu` VALUES (15, 10, 0, 1, '富文本', 'Editor', 'components/Editor', 52, 'fwb', 'tinymce', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-27 11:58:25', NULL);
 * INSERT INTO `sys_menu` VALUES (18, 36, 3, 1, '存储管理', 'Storage', 'tools/storage/index', 34, 'qiniu', 'storage', b'0', b'0', b'0', 'storage:list', NULL, NULL, '2018-12-31 11:12:15', NULL);
 * INSERT INTO `sys_menu` VALUES (19, 36, 0, 1, '支付宝工具', 'AliPay', 'tools/aliPay/index', 37, 'alipay', 'aliPay', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-31 14:52:38', NULL);
 * INSERT INTO `sys_menu` VALUES (21, NULL, 2, 0, '多级菜单', NULL, '', 900, 'menu', 'nested', b'0', b'0', b'0', NULL, NULL, 'admin', '2019-01-04 16:22:03', '2020-06-21 17:27:35');
 * INSERT INTO `sys_menu` VALUES (22, 21, 2, 0, '二级菜单1', NULL, '', 999, 'menu', 'menu1', b'0', b'0', b'0', NULL, NULL, 'admin', '2019-01-04 16:23:29', '2020-06-21 17:27:20');
 * INSERT INTO `sys_menu` VALUES (23, 21, 0, 1, '二级菜单2', NULL, 'nested/menu2/index', 999, 'menu', 'menu2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-04 16:23:57', NULL);
 * INSERT INTO `sys_menu` VALUES (24, 22, 0, 1, '三级菜单1', 'Test', 'nested/menu1/menu1-1', 999, 'menu', 'menu1-1', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-04 16:24:48', NULL);
 * INSERT INTO `sys_menu` VALUES (27, 22, 0, 1, '三级菜单2', NULL, 'nested/menu1/menu1-2', 999, 'menu', 'menu1-2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-07 17:27:32', NULL);
 * INSERT INTO `sys_menu` VALUES (28, 1, 3, 1, '任务调度', 'Timing', 'system/timing/index', 999, 'timing', 'timing', b'0', b'0', b'0', 'timing:list', NULL, NULL, '2019-01-07 20:34:40', NULL);
 * INSERT INTO `sys_menu` VALUES (30, 36, 0, 1, '代码生成', 'GeneratorIndex', 'generator/index', 32, 'dev', 'generator', b'0', b'1', b'0', NULL, NULL, NULL, '2019-01-11 15:45:55', NULL);
 * INSERT INTO `sys_menu` VALUES (32, 6, 0, 1, '异常日志', 'ErrorLog', 'monitor/log/errorLog', 12, 'error', 'errorLog', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-13 13:49:03', NULL);
 * INSERT INTO `sys_menu` VALUES (33, 10, 0, 1, 'Markdown', 'Markdown', 'components/MarkDown', 53, 'markdown', 'markdown', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-08 13:46:44', NULL);
 * INSERT INTO `sys_menu` VALUES (34, 10, 0, 1, 'Yaml编辑器', 'YamlEdit', 'components/YamlEdit', 54, 'dev', 'yaml', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-08 15:49:40', NULL);
 * INSERT INTO `sys_menu` VALUES (35, 1, 3, 1, '部门管理', 'Dept', 'system/dept/index', 6, 'dept', 'dept', b'0', b'0', b'0', 'dept:list', NULL, NULL, '2019-03-25 09:46:00', NULL);
 * INSERT INTO `sys_menu` VALUES (36, NULL, 7, 0, '系统工具', NULL, '', 30, 'sys-tools', 'sys-tools', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-29 10:57:35', NULL);
 * INSERT INTO `sys_menu` VALUES (37, 1, 3, 1, '岗位管理', 'Job', 'system/job/index', 7, 'Steve-Jobs', 'job', b'0', b'0', b'0', 'job:list', NULL, NULL, '2019-03-29 13:51:18', NULL);
 * INSERT INTO `sys_menu` VALUES (38, 36, 0, 1, '接口文档', 'Swagger', 'tools/swagger/index', 36, 'swagger', 'swagger2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-29 19:57:53', NULL);
 * INSERT INTO `sys_menu` VALUES (39, 1, 3, 1, '字典管理', 'Dict', 'system/dict/index', 8, 'dictionary', 'dict', b'0', b'0', b'0', 'dict:list', NULL, NULL, '2019-04-10 11:49:04', NULL);
 * INSERT INTO `sys_menu` VALUES (41, 6, 0, 1, '在线用户', 'OnlineUser', 'monitor/online/index', 10, 'Steve-Jobs', 'online', b'0', b'0', b'0', NULL, NULL, NULL, '2019-10-26 22:08:43', NULL);
 * INSERT INTO `sys_menu` VALUES (44, 2, 0, 2, '用户新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'user:add', NULL, NULL, '2019-10-29 10:59:46', NULL);
 * INSERT INTO `sys_menu` VALUES (45, 2, 0, 2, '用户编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'user:edit', NULL, NULL, '2019-10-29 11:00:08', NULL);
 * INSERT INTO `sys_menu` VALUES (46, 2, 0, 2, '用户删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'user:del', NULL, NULL, '2019-10-29 11:00:23', NULL);
 * INSERT INTO `sys_menu` VALUES (48, 3, 0, 2, '角色创建', NULL, '', 2, '', '', b'0', b'0', b'0', 'roles:add', NULL, NULL, '2019-10-29 12:45:34', NULL);
 * INSERT INTO `sys_menu` VALUES (49, 3, 0, 2, '角色修改', NULL, '', 3, '', '', b'0', b'0', b'0', 'roles:edit', NULL, NULL, '2019-10-29 12:46:16', NULL);
 * INSERT INTO `sys_menu` VALUES (50, 3, 0, 2, '角色删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'roles:del', NULL, NULL, '2019-10-29 12:46:51', NULL);
 * INSERT INTO `sys_menu` VALUES (52, 5, 0, 2, '菜单新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'menu:add', NULL, NULL, '2019-10-29 12:55:07', NULL);
 * INSERT INTO `sys_menu` VALUES (53, 5, 0, 2, '菜单编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'menu:edit', NULL, NULL, '2019-10-29 12:55:40', NULL);
 * INSERT INTO `sys_menu` VALUES (54, 5, 0, 2, '菜单删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'menu:del', NULL, NULL, '2019-10-29 12:56:00', NULL);
 * INSERT INTO `sys_menu` VALUES (56, 35, 0, 2, '部门新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'dept:add', NULL, NULL, '2019-10-29 12:57:09', NULL);
 * INSERT INTO `sys_menu` VALUES (57, 35, 0, 2, '部门编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'dept:edit', NULL, NULL, '2019-10-29 12:57:27', NULL);
 * INSERT INTO `sys_menu` VALUES (58, 35, 0, 2, '部门删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'dept:del', NULL, NULL, '2019-10-29 12:57:41', NULL);
 * INSERT INTO `sys_menu` VALUES (60, 37, 0, 2, '岗位新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'job:add', NULL, NULL, '2019-10-29 12:58:27', NULL);
 * INSERT INTO `sys_menu` VALUES (61, 37, 0, 2, '岗位编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'job:edit', NULL, NULL, '2019-10-29 12:58:45', NULL);
 * INSERT INTO `sys_menu` VALUES (62, 37, 0, 2, '岗位删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'job:del', NULL, NULL, '2019-10-29 12:59:04', NULL);
 * INSERT INTO `sys_menu` VALUES (64, 39, 0, 2, '字典新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'dict:add', NULL, NULL, '2019-10-29 13:00:17', NULL);
 * INSERT INTO `sys_menu` VALUES (65, 39, 0, 2, '字典编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'dict:edit', NULL, NULL, '2019-10-29 13:00:42', NULL);
 * INSERT INTO `sys_menu` VALUES (66, 39, 0, 2, '字典删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'dict:del', NULL, NULL, '2019-10-29 13:00:59', NULL);
 * INSERT INTO `sys_menu` VALUES (73, 28, 0, 2, '任务新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'timing:add', NULL, NULL, '2019-10-29 13:07:28', NULL);
 * INSERT INTO `sys_menu` VALUES (74, 28, 0, 2, '任务编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'timing:edit', NULL, NULL, '2019-10-29 13:07:41', NULL);
 * INSERT INTO `sys_menu` VALUES (75, 28, 0, 2, '任务删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'timing:del', NULL, NULL, '2019-10-29 13:07:54', NULL);
 * INSERT INTO `sys_menu` VALUES (77, 18, 0, 2, '上传文件', NULL, '', 2, '', '', b'0', b'0', b'0', 'storage:add', NULL, NULL, '2019-10-29 13:09:09', NULL);
 * INSERT INTO `sys_menu` VALUES (78, 18, 0, 2, '文件编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'storage:edit', NULL, NULL, '2019-10-29 13:09:22', NULL);
 * INSERT INTO `sys_menu` VALUES (79, 18, 0, 2, '文件删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'storage:del', NULL, NULL, '2019-10-29 13:09:34', NULL);
 * INSERT INTO `sys_menu` VALUES (80, 6, 0, 1, '服务监控', 'ServerMonitor', 'monitor/server/index', 14, 'codeConsole', 'server', b'0', b'0', b'0', 'monitor:list', NULL, 'admin', '2019-11-07 13:06:39', '2020-05-04 18:20:50');
 * INSERT INTO `sys_menu` VALUES (82, 36, 0, 1, '生成配置', 'GeneratorConfig', 'generator/config', 33, 'dev', 'generator/config/:tableName', b'0', b'1', b'1', '', NULL, NULL, '2019-11-17 20:08:56', NULL);
 * INSERT INTO `sys_menu` VALUES (83, 10, 0, 1, '图表库', 'Echarts', 'components/Echarts', 50, 'chart', 'echarts', b'0', b'1', b'0', '', NULL, NULL, '2019-11-21 09:04:32', NULL);
 * INSERT INTO `sys_menu` VALUES (90, NULL, 5, 1, '运维管理', 'Mnt', '', 20, 'mnt', 'mnt', b'0', b'0', b'0', NULL, NULL, NULL, '2019-11-09 10:31:08', NULL);
 * INSERT INTO `sys_menu` VALUES (92, 90, 3, 1, '服务器', 'ServerDeploy', 'mnt/server/index', 22, 'server', 'mnt/serverDeploy', b'0', b'0', b'0', 'serverDeploy:list', NULL, NULL, '2019-11-10 10:29:25', NULL);
 * INSERT INTO `sys_menu` VALUES (93, 90, 3, 1, '应用管理', 'App', 'mnt/app/index', 23, 'app', 'mnt/app', b'0', b'0', b'0', 'app:list', NULL, NULL, '2019-11-10 11:05:16', NULL);
 * INSERT INTO `sys_menu` VALUES (94, 90, 3, 1, '部署管理', 'Deploy', 'mnt/deploy/index', 24, 'deploy', 'mnt/deploy', b'0', b'0', b'0', 'deploy:list', NULL, NULL, '2019-11-10 15:56:55', NULL);
 * INSERT INTO `sys_menu` VALUES (97, 90, 1, 1, '部署备份', 'DeployHistory', 'mnt/deployHistory/index', 25, 'backup', 'mnt/deployHistory', b'0', b'0', b'0', 'deployHistory:list', NULL, NULL, '2019-11-10 16:49:44', NULL);
 * INSERT INTO `sys_menu` VALUES (98, 90, 3, 1, '数据库管理', 'Database', 'mnt/database/index', 26, 'database', 'mnt/database', b'0', b'0', b'0', 'database:list', NULL, NULL, '2019-11-10 20:40:04', NULL);
 * INSERT INTO `sys_menu` VALUES (102, 97, 0, 2, '删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'deployHistory:del', NULL, NULL, '2019-11-17 09:32:48', NULL);
 * INSERT INTO `sys_menu` VALUES (103, 92, 0, 2, '服务器新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:add', NULL, NULL, '2019-11-17 11:08:33', NULL);
 * INSERT INTO `sys_menu` VALUES (104, 92, 0, 2, '服务器编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:edit', NULL, NULL, '2019-11-17 11:08:57', NULL);
 * INSERT INTO `sys_menu` VALUES (105, 92, 0, 2, '服务器删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:del', NULL, NULL, '2019-11-17 11:09:15', NULL);
 * INSERT INTO `sys_menu` VALUES (106, 93, 0, 2, '应用新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:add', NULL, NULL, '2019-11-17 11:10:03', NULL);
 * INSERT INTO `sys_menu` VALUES (107, 93, 0, 2, '应用编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:edit', NULL, NULL, '2019-11-17 11:10:28', NULL);
 * INSERT INTO `sys_menu` VALUES (108, 93, 0, 2, '应用删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:del', NULL, NULL, '2019-11-17 11:10:55', NULL);
 * INSERT INTO `sys_menu` VALUES (109, 94, 0, 2, '部署新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:add', NULL, NULL, '2019-11-17 11:11:22', NULL);
 * INSERT INTO `sys_menu` VALUES (110, 94, 0, 2, '部署编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:edit', NULL, NULL, '2019-11-17 11:11:41', NULL);
 * INSERT INTO `sys_menu` VALUES (111, 94, 0, 2, '部署删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:del', NULL, NULL, '2019-11-17 11:12:01', NULL);
 * INSERT INTO `sys_menu` VALUES (112, 98, 0, 2, '数据库新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:add', NULL, NULL, '2019-11-17 11:12:43', NULL);
 * INSERT INTO `sys_menu` VALUES (113, 98, 0, 2, '数据库编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:edit', NULL, NULL, '2019-11-17 11:12:58', NULL);
 * INSERT INTO `sys_menu` VALUES (114, 98, 0, 2, '数据库删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:del', NULL, NULL, '2019-11-17 11:13:14', NULL);
 * INSERT INTO `sys_menu` VALUES (116, 36, 0, 1, '生成预览', 'Preview', 'generator/preview', 999, 'java', 'generator/preview/:tableName', b'0', b'1', b'1', NULL, NULL, NULL, '2019-11-26 14:54:36', NULL);
 *
 * **/