<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="父id" prop="newsMenuParentId">
        <el-input
          v-model="queryParams.newsMenuParentId"
          placeholder="请输入父id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="菜单名称" prop="newsMenuName">
        <el-input
          v-model="queryParams.newsMenuName"
          placeholder="请输入菜单名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="菜单类型" prop="newsMenuType">
        <el-select v-model="queryParams.newsMenuType" placeholder="请选择菜单类型" clearable>
          <el-option
            v-for="dict in dict.type.news_menu_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="菜单图标" prop="newsMenuIcon">
        <el-input
          v-model="queryParams.newsMenuIcon"
          placeholder="请输入菜单图标"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="显示" prop="newsMenuVisibel">
        <el-select v-model="queryParams.newsMenuVisibel" placeholder="请选择显示" clearable>
          <el-option
            v-for="dict in dict.type.news_menu_visible"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
	    <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['news:menu:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-sort"
          size="mini"
          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="menuList"
      row-key="newsMenuId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="父id" prop="newsMenuParentId" />
      <el-table-column label="菜单名称" align="center" prop="newsMenuName" />
      <el-table-column label="菜单类型" align="center" prop="newsMenuType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.news_menu_type" :value="scope.row.newsMenuType"/>
        </template>
      </el-table-column>
      <el-table-column label="菜单图标" align="center" prop="newsMenuIcon" />
      <el-table-column label="显示" align="center" prop="newsMenuVisibel">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.news_menu_visible" :value="scope.row.newsMenuVisibel"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['news:menu:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['news:menu:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['news:menu:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改新闻菜单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="父id" prop="newsMenuParentId">
          <treeselect v-model="form.newsMenuParentId" :options="menuOptions" :normalizer="normalizer" placeholder="请选择父id" />
        </el-form-item>
        <el-form-item label="菜单名称" prop="newsMenuName">
          <el-input v-model="form.newsMenuName" placeholder="请输入菜单名称" />
        </el-form-item>
        <el-form-item label="菜单类型" prop="newsMenuType">
          <el-select v-model="form.newsMenuType" placeholder="请选择菜单类型">
            <el-option
              v-for="dict in dict.type.news_menu_type"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="菜单图标" prop="newsMenuIcon">
          <el-input v-model="form.newsMenuIcon" placeholder="请输入菜单图标" />
        </el-form-item>
        <el-form-item label="显示" prop="newsMenuVisibel">
          <el-select v-model="form.newsMenuVisibel" placeholder="请选择显示">
            <el-option
              v-for="dict in dict.type.news_menu_visible"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMenu, getMenu, delMenu, addMenu, updateMenu } from "@/api/news/menu";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Menu",
  dicts: ['news_menu_type', 'news_menu_visible'],
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 新闻菜单表格数据
      menuList: [],
      // 新闻菜单树选项
      menuOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams: {
        newsMenuParentId: null,
        newsMenuName: null,
        newsMenuType: null,
        newsMenuIcon: null,
        newsMenuVisibel: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询新闻菜单列表 */
    getList() {
      this.loading = true;
      listMenu(this.queryParams).then(response => {
        this.menuList = this.handleTree(response.data, "newsMenuId", "newsMenuParentId");
        this.loading = false;
      });
    },
    /** 转换新闻菜单数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.newsMenuId,
        label: node.newsMenuName,
        children: node.children
      };
    },
	/** 查询新闻菜单下拉树结构 */
    getTreeselect() {
      listMenu().then(response => {
        this.menuOptions = [];
        const data = { newsMenuId: 0, newsMenuName: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "newsMenuId", "newsMenuParentId");
        this.menuOptions.push(data);
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        newsMenuId: null,
        newsMenuParentId: null,
        newsMenuName: null,
        newsMenuType: null,
        newsMenuIcon: null,
        newsMenuVisibel: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.newsMenuId) {
        this.form.newsMenuParentId = row.newsMenuId;
      } else {
        this.form.newsMenuParentId = 0;
      }
      this.open = true;
      this.title = "添加新闻菜单";
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.newsMenuParentId = row.newsMenuId;
      }
      getMenu(row.newsMenuId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改新闻菜单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.newsMenuId != null) {
            updateMenu(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMenu(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$modal.confirm('是否确认删除新闻菜单编号为"' + row.newsMenuId + '"的数据项？').then(function() {
        return delMenu(row.newsMenuId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
