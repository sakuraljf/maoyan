<template>
	<div>
		<el-container>
		  <el-header height="150px" >
				<el-steps :space="200" :active="value" finish-status="success">
				  <el-step title="选择影片场次" :align-center="true"></el-step>
				  <el-step title="选择座位" :align-center="true"></el-step>
				  <el-step title="14分钟内付款" :align-center="true"></el-step>
					<el-step title="影院取票观影" :align-center="true"></el-step>
				</el-steps>
			</el-header>
		  <el-container v-if="issus===false">
		    <el-main>
					<hr />
					<span class="zi">银幕中央</span>
				<div class="xuanbox">
					<div v-for="(o, indexs) in 8" :key="o" class="rowzuowei" >
					  <span v-for="(p, index) in 8" :key="p" class="zuowei" @click="getcol(indexs+1,index+1)" :class="{changeStyle:changeSelectStyle==(indexs+1)*10+(index+1)}">
							<i class=" el-icon-star-off"></i>
					  </span>
					</div>
				</div>
				
				</el-main>
				<el-aside width="400px">
					<div class="Dingdetail">
						<img class="Dingimg" :src="film.poster" />
						<div class="Dingdetailmes">
							<div style="font-size: 24px; margin-bottom: 10px;">{{film.name}}</div>
							<div style="font-size: 12px; color: silver;margin-bottom:5px;">类型 : {{film.category}}</div>
							<div style="font-size: 12px; color: silver;">时长 : {{film.runtime}}分钟</div>
							<div class="yinyuanmes">
								<div>影院:{{yinyuandetail.name}}</div>
								<div>影厅:6号厅</div>
								<div>版本:国语2D</div>
								<div>场次:今天下午2:00</div>
								<div>售价:28/张</div>
							</div>
						</div>
					</div>
					<div class="zuomes" >
						<span style="margin-top: 20px;margin-left: 10px;">座位:</span>
						<span v-if="row==0&&col==0" style="margin-top: 20px;">请在最左边选择座位</span>
						<span v-else class="zuoweibq" style="margin-top: 20px;">{{row}}排{{col}}座</span>
						<div style="height: 20px;"></div>
						<span  style="margin-top:20px;margin-left: 10px;">总价:</span>
						<span  style="color:red;font-size:24px;margin-left: 10px;">￥28</span>
					</div>
					<div class="pay">
						<div class="confrim" @click="payconfirm">确认选座</div>
					</div>
				</el-aside>
		  </el-container>
			<el-empty description="请及时前往影院取票" v-else></el-empty>
		</el-container>
		
	</div>
</template>

<script>
	export default{
		name:'Ding',
		data(){
			return {
				row:0,
				col:0,
				changeSelectStyle:0,
				film:[],
				value:2,
				issus:false,
				yinyuandetail:[]
			}
		},
	mounted() {
		console.log(this.$route.query)
		this.$nextTick(function(){
			var that=this;
			var cinemaId=that.$route.query.cinemaId;
			var filmId=that.$route.query.filmId;
				this.$axios.get('api/getFilmInfo?filmId='+filmId).then(
				function(response){
					console.log(response.data.data.film);
					that.film=response.data.data.film;
				}).catch(
				function (error) {
				    console.log(error);
				  }
				)
				// 发起影院请求
				this.$axios.get('api/getCinemaInfo?cinemaId='+cinemaId).then(
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
	},
		methods:{
			getcol(index,colunm){
				this.row=index;
				this.col=colunm;
				this.changeSelectStyle=(index)*10+colunm;
				console.log('点击'+index+"行",colunm+'列')
			},
			payconfirm(){
				if(sessionStorage.getItem('user')==null){
					alert('请先登录');
				}
				else if(this.row==0&&this.col==0){
					alert("请选择座位后提交订单")
				}
				else{
					var r=confirm('确认进行支付吗？');
					if(r==true){
						var date=new Date();
						var mouth=date.getMonth()+1;
						var day=date.getDate();
						var hour=date.getHours();
						var minute=date.getMinutes();
						var second=date.getSeconds();
						//时间
						var time=mouth+'月'+day+'日'+hour+'时'+minute+'分钟'+second+'秒'
						//用户名
						var username=sessionStorage.getItem('user');
						//影院名
						var yuanName=this.yinyuandetail.name;
						//影院id
						var filmId=this.$route.query.filmId;
						//电影名
						var filmName=this.film.name;
						//座位位置
						var locations=this.row+'排'+this.col+'座';
						
						let param=new URLSearchParams();
						param.append('username',username);
						param.append('filmName',filmName);
						param.append('filmId',filmId);
						param.append('time',time);
						param.append('yuanName',yuanName);
						param.append('locations',locations);
						console.log(time,username,yuanName,filmId,filmName,locations);
						var that=this;
						this.$axios.post('apl/Lj/Ding',param)
						  .then(function (response) {
						    console.log(response);
								console.log("增加订单成功");
								that.value++;
								that.issus=true;
						  })
						  .catch(function (error) {
						    console.log(error);
						  });
					}else{
						console.log('用户取消了支付');
					}
		
				}
			}
		}
	}
</script>

<style>
	.el-header, .el-footer {
	    background-color: #B3C0D1;
	    color: #333;
			width: 100%;
	  }
		.el-header{
			background-color: white;
		}
	  .el-steps{
		margin-left: 20%;
		margin-top: 30px;
		}
	  .el-aside {
	    background-color:rgb(249,249,249);
	    color: #333;
	  }
	  
	  .el-main {
	    color: #333;
	  }
	  
	  body > .el-container {
	    margin-bottom: 40px;
	  }
	  
	  .el-container:nth-child(5) .el-aside,
	  .el-container:nth-child(6) .el-aside {
	    line-height: 260px;
	  }
	  
	  .el-container:nth-child(7) .el-aside {
	    line-height: 320px;
	  }
		.zuowei{
			width: 30px;
			height:30px;
			display: inline-block;
			margin-left: 5px;
			text-align: center;
		}
		.rowzuowei{
			width: 300px;
			height: 30px;
			margin: auto;
			margin-bottom: 5px;
		}
		
		.changeStyle{
			background-color: rgb(103,194,58);
		}
		.zi{
		margin-left: 350px;
		}
		.xuanbox{
			margin-top: 20px;
		}
		.Dingdetail{
		/* height: 600px; */
		overflow: hidden;
		margin-top: 20px;
		}
		.zuomes{
			border-top: 1px dashed silver;
			padding-bottom: 20px;
			border-bottom: 1px dashed silver;
		}
		.zuoweibq{
			display: inline-block;
			border: 1px solid red;
			background-color: white;
			border-radius: 3px;
			color: red;
		}
		.Dingimg{
			width: 150px;
			display: inline-block;
			float: left;
		}
		.Dingdetailmes{
			width: 45%;
	    margin-left: 20px;
			float: left;
			
		}
		.confrim{
			width: 60%;
			height: 46px;
			border-radius: 23px;
			background-color: red;
			color: white;
			text-align: center;
			line-height: 46px;
		}
		.pay{
			height: 100px;
			margin: auto;
			align-items: center;
			display: flex;
			justify-content: center;
		}
		
</style>
