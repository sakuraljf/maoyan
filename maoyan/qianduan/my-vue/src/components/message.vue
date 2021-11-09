<template>
	<div>
		<div class="dinglistbox" v-if="issuc==true">
			<div class="list" v-for="item in data" :key=item.id>
				<div class="dingmes">
					<div class="mes">{{item.filmName}}</div>
					<div class="mes">{{item.yuanName}}</div>
					<div class="mes">{{item.time}}</div>
					<div class="mes">{{item.locations}}</div>
				</div>
				<div class="pingjia">
					<router-link :to="'/ping?filmId='+item.filmId+'&time='+item.time+'&filmName='+item.filmName" class="ljf">评价影片</router-link>
					</div>
			</div>
		</div>
		<el-empty description="请先登录" v-else></el-empty>
	</div>
</template>

<script>
	export default{
		name:'message',
		data(){
			return{
				issuc:false,
				data:[]
			}
		},
		mounted() {
			var username=sessionStorage.getItem('user');
			if(username!==null){
				this.issuc=true;
				this.$nextTick(function(){
					var that=this;
					let param=new URLSearchParams();
					param.append('username',username);
					this.$axios.post('apl/Lj/lists',param)
					  .then(function (response) {
					    console.log(response.data);
							that.data=response.data;
					  })
					  .catch(function (error) {
					    console.log(error);
					  });
				})
			}
      else{
				alert('请先登录');
			}
		}
	}
</script>

<style>
	.list{
		width: 100%;
		border-bottom: 1px dashed silver;
		overflow: hidden;
	}
	.dingmes{
		width: 200px;
		height: 180px;
		float: left;
	}
	.pingjia{
		width: 120px;
		height:40px;
		float: right;
		margin-top:130px;
		border-radius:20px;
		background-color: red;
		color: white;
		text-align: center;
		line-height: 40px;
	}
	.mes{
		margin-bottom: 10px;
	}
	.ljf{
		text-decoration: none;
		color: white;
	}
</style>
