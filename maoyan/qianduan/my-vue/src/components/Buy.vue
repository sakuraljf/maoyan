<template>
	<div>
		<div class="detailmains">
			<img :src="yinyuandetail.logoUrl" class="buyimg"/>
			<div class="buybox1">
				<div class="buyname">{{yinyuandetail.name}}</div>
				<div class="buylocation">{{yinyuandetail.address}}</div>
				<div class="buyphone">电话:{{yinyuandetail.phone}}</div>
				<div class="buyfu">影院服务</div>
				<hr />
				<div>
					<span class="tubiao">退</span>
					<span class="wenzi">未取票用户放映前60分钟可退票</span>
				</div>
				<div>
					<span class="tubiao">改签</span>
					<span class="wenzi">未取票用户放映前60分钟可改签</span>
				</div>
				<div>
					<span class="tubiao">3D眼镜</span>
					<span class="wenzi">免费赠送全新一次性3D眼镜</span>
				</div>
				<div>
					<span class="tubiao">儿童优惠</span>
					<span class="wenzi">1.3米以下免费。特殊厅儿童需购票</span>
				</div>
				<div>
					<span class="tubiao">可停车</span>
					<span class="wenzi">凭当日2张电影票免费停车3个小时，不可叠加使用。</span>
				</div>
			</div>
		</div>
		
		<div class="paipian">
<template>
  <el-table
    :data="tableData"
    border
		:stripe='true'
    style="width: 100%">
    <el-table-column
      fixed
      prop="time"
      label="放映时间"
      width="200">
    </el-table-column>
    <el-table-column
      prop="filmLanguage"
      label="语言版本"
      width="200">
    </el-table-column>
    <el-table-column
      prop="hallName"
      label="放映厅"
      width="200">
    </el-table-column>
    <el-table-column
      prop="Price"
      label="售价"
      width="200">
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="200">
      <template slot-scope="scope">
        <!-- <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button> -->
        <el-button type="text" size="small">
					<router-link :to="'/Ding?cinemaId='+mes.cinemaId+'&filmId='+mes.filmId">选座购票</router-link>
				</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>
		</div>
	</div>
</template>

<script>
	export default{
		name:'Buy',
		data(){
			return {
				yinyuandetail:[],
        tableData: [],
				mes:{}
			}
		},
		 methods: {
		      handleClick(row) {
		        console.log(row);
		      }
		 },
		mounted() {
		this.$nextTick(function(){
			var that=this;
			var mes=that.$route.query;
				this.$axios.get('api/getCinemaInfo?cinemaId='+mes.cinemaId).then(
				function(response){
					console.log(response.data.data.cinema);
					that.yinyuandetail=response.data.data.cinema
				}).catch(
				function (error) {
				    // 处理错误情况
				    console.log(error);
				  }
				)
		})
		
		this.$nextTick(function(){
			var that=this;
			var mes=that.$route.query;
			that.mes=mes;
				this.$axios.get("api/getCinemaFilmSchedules?cinemaId="+mes.cinemaId+'&'+'filmId='+mes.filmId).then(
				function(response){
					var datalist=response.data.data.schedules
				  datalist.map(item=>{
						item.Price='￥'+parseInt((item.marketPrice-item.salePrice)/50);
						item.time='2:00'
					})
					console.log(datalist)
					that.tableData=datalist;
				}).catch(
				function (error) {
				    // 处理错误情况
				    console.log(error);
				  }
				)
		})
		}
	}
</script>

<style>
	.detailmains{
		background: -webkit-radial-gradient(top left,ellipse,#744550,#3A2F59);
		width: 100%;
		position: absolute;
		height: 350px;
	}
	.buyimg{
		float: left;
		margin-top:60px;
		margin-left: 100px;
	}
	.buybox1{
		float: left;
		margin-left: 20px;
		margin-top:60px;
	}
	.buyname{
		font-size: 24px;
		margin-bottom: 10px;
		color: white;
	}
	.buylocation{
		color: white;
		margin-bottom: 8px;
	}
	.buyphone{
		color: white;
		margin-bottom: 20px;
	}
	.buyfu{
		color: white;
	}
	.tubiao{
		display: inline-block;
		border: 1px solid white;
		border-radius: 5px;
		color: white;
		font-size: 12px;
		margin-right: 5px;
		width: 50px;
		text-align: center;
		margin-bottom: 3px;
	}
	.wenzi{
		color: white;
		font-size: 8px;
		margin-bottom: 3px;
	}
	.paipian{
		position: absolute;
		margin-top: 360px;
	}
	td.el-table_1_column_4 div{
		color: red !important;
	}
</style>
