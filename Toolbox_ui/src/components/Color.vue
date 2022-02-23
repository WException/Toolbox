<template>
	<el-scrollbar style="height:100%">
		<div class="color_body">
			<el-row>
				<el-col :span="4" class="aa" v-for="(color,index) in colors" :key="color.label"
						:style="{backgroundColor: color.item}"
						@click.native="copyColor(color)" @mouseover.native="selectStyle(index)"
						@mouseout.native="outStyle(index)">
					<div class="text hidden-md-and-down">{{ color.name }}</div>
					<transition name="el-fade-in-linear">
						<div class="copy-box" v-if="color.isHover">copy</div>
					</transition>
				</el-col>
			</el-row>
			<main :style="{backgroundColor: main.bgcolor}" v-if="main.is">
				<div class="main-box1">{{ main.text }}</div>
				<div class="main-box2">{{ main.text2 }}</div>
			</main>
		</div>
	</el-scrollbar>
</template>

<script>
import 'element-ui/lib/theme-chalk/display.css';

export default {
	name: "Color",
	data() {
		return {
			colors: [{
				item: "#1abc9c",
				name: "TUROUOISE",
				isHover: false,
			}, {
				item: "#2ecc71",
				name: "EMERALD",
				isHover: false,
			}, {
				item: "#3498db",
				name: "PETER RIVER",
				isHover: false,
			}, {
				item: "#9b59b6",
				name: "AMETHYST",
				isHover: false,
			}, {
				item: "#34495e",
				name: "WET ASPHALT",
				isHover: false,
			}, {
				item: "#2c3e50",
				name: "MIDNIGHT BLUE",
				isHover: false,
			},
			// 第2行
			{
				item: "#16a085",
				name: "GREEN SEA",
				isHover: false,
			}, {
				item: "#27ae60",
				name: "NEPHRITIS",
				isHover: false,
			}, {
				item: "#2980b9",
				name: "BELIZE HOLE",
				isHover: false,
			}, {
				item: "#8e44ad",
				name: "WISTERIA",
				isHover: false,
			}, {
				item: "#95a5a6",
				name: "CONCRETE",
				isHover: false,
			}, {
				item: "#7f8c8d",
				name: "ASBESTOS",
				isHover: false,
			},
			// 第3行
			{
				item: "#f1c40f",
				name: "SUN FLOWER",
				isHover: false,
			}, {
				item: "#e67e22",
				name: "CARROT",
				isHover: false,
			}, {
				item: "#e74c3c",
				name: "ALIZARIN",
				isHover: false,
			}, {
				item: "#0652DD",
				name: "MERCHANT MARINE BLUE",
				isHover: false,
			}, {
				item: "#bdc3c7",
				name: "CLOUDS",
				isHover: false,
			}, {
				item: "#ecf0f1",
				name: "SILVER",
				isHover: false,
			},
			// 第4行
			{
				item: "#f39c12",
				name: "ORANGE",
				isHover: false,
			}, {
				item: "#d35400",
				name: "PUMPKIN",
				isHover: false,
			}, {
				item: "#c0392b",
				name: "POMEGRANATE",
				isHover: false,
			}, {
				item: "#1B1464",
				name: "LEAGUES UNDER THE SEA",
				isHover: false,
			}, {
				item: "#9980FA",
				name: "FORGOTTEN PURPLE",
				isHover: false,
			}, {
				item: "#5758BB",
					name: "CIRCUMORBITAL RING",
				isHover: false,
			},
			//第5行
			{
				item:"#f6e58d",
				name:"BEEKEEPER",
				isHover:false,
			},{
				item:"#ffbe76",
				name:"SPICED NECTARINE",
				isHover:false,
			},{
				item:"#ff7979",
				name:"PINK GLAMOUR",
				isHover:false,
			},{
				item:"#badc58",
				name:"JUNE BUD",
				isHover:false,
			},{
				item:"#dff9fb",
				name:"COASTAL BREEZE",
				isHover:false,
			},{
				item:"#7ed6df",
				name:"MIDDLE BLUE",
				isHover:false,
			},
			//第6行
			{
				item:"#f9ca24",
				name:"TURBO",
				isHover:false,
			},{
				item:"#f0932b",
				name:"UINCE JELLY",
				isHover:false,
			},{
				item:"#eb4d4b",
				name:"CARMINE PINK",
				isHover:false,
			},{
				item:"#6ab04c",
				name:"PURE APPLE",
				isHover:false,
			},{
				item:"#c7ecee",
				name:"HINT OFICE PACK",
				isHover:false,
			},{
				item:"#22a6b3",
				name:"GREENLAND GREEN",
				isHover:false,
			},
			//第7行
			{
				item:"#e056fd",
				name:"HELIOTROPE",
				isHover:false,
			},{
				item:"#686de0",
				name:"EXODUS FRUIT",
				isHover:false,
			},{
				item:"#30336b",
				name:"DEEP KOAMARU",
				isHover:false,
			},{
				item:"#95afc0",
				name:"SOARING EAGLE",
				isHover:false,
			},{
				item:"#dfe4ea",
				name:"CITY LIGHTS",
				isHover:false,
			},{
				item:"#ffffff",
				name:"White",
				isHover:false,
			},
			//第8行
			{
				item:"#be2edd",
				name:"STEEL PINK",
				isHover:false,
			},{
				item:"#4834d4",
				name:"STEEL PINK",
				isHover:false,
			},{
				item:"#130f40",
				name:"DEEP COVE",
				isHover:false,
			},{
				item:"#535c68",
				name:"WIZARD GREY",
				isHover:false,
			},{
				item:"#ced6e0",
				name:"TWINKLEBLUE",
				isHover:false,
			},{
				item:"#f1f2f6",
				name:"ANTIHFLASH WHITE",
				isHover:false,
			},
			//第9行
			{
				item:"#00a8ff",
				name:"PROTOsS PYLON",
				isHover:false,
			},{
				item:"#9c88ff",
				name:"PERIWINKLE",
				isHover:false,
			},{
				item:"#fbc531",
				name:"RISE-N-SHINE",
				isHover:false,
			},{
				item:"#4cd137",
				name:"DOWNLOAD PROGRESS",
				isHover:false,
			},{
				item:"#487eb0",
				name:"SEABROOK",
				isHover:false,
			},{
				item:"#273c75",
				name:"MAZARINE BLUE",
				isHover:false,
			},
			//第10行
			{
				item:"#0097e6",
				name:"VANADYL BLUE",
				isHover:false,
			},{
				item:"#8c7ae6",
				name:"MATT PURPLE",
				isHover:false,
			},{
				item:"#e1b12c",
				name:"NANOHANACHA GOLD",
				isHover:false,
			},{
				item:"#44bd32",
				name:"SKIRRET GREEN",
				isHover:false,
			},{
				item:"#40739e",
				name:"NAVAL",
				isHover:false,
			},{
				item:"#192a56",
				name:"PICO VoID",
				isHover:false,
			},
			//第11行
			{
				item:"#f3a683",
				name:"CREAMY PEACH",
				isHover:false,
			},{
				item:"#f7d794",
				name:"ROSY HIGHLIGHT",
				isHover:false,
			},{
				item:"#e77f67",
				name:"BREWED MUSTARD",
				isHover:false,
			},{
				item:"#f8a5c2",
				name:"ROGUE PINK",
				isHover:false,
			},{
				item:"#ea8685",
				name:"APPLE VALLEY",
				isHover:false,
			},{
				item:"#cf6a87",
				name:"OLD GERANIUM",
				isHover:false,
			},
			//第12行
			{
				item:"#f3a683",
				name:"SAWTOOTH AAK",
				isHover:false,
			},{
				item:"#f5cd79",
				name:"SUMMERTIME",
				isHover:false,
			},{
				item:"#e15f41",
				name:"TIGERLILY",
				isHover:false,
			},{
				item:"#f78fb3",
				name:"FLAMINGO PINK",
				isHover:false,
			},{
				item:"#e66767",
				name:"PORCE",
				isHover:false,
			},{
				item:"#c44569",
				name:"DEEP ROSE",
				isHover:false,
			}],
			main: {
				is: false,
				bgcolor: "",
				text: "",
				text2: ""
			},
		}
	},
	methods: {
		copyColor(e) {
			navigator.clipboard.writeText(e.item);
			this.main.is = true
			this.main.bgcolor = e.item
			this.main.text = e.name
			this.main.text2 = e.item
			setTimeout(() => {
				this.main.is = false
			}, 1000)
		},
		selectStyle(e) {
			this.colors[e].isHover = true
		},
		outStyle(e) {
			this.colors[e].isHover = false
		}
	}
}
</script>

<style scoped>
.color_body {
	box-sizing: border-box;
	width: 100vw;
	background-color: aquamarine;
	margin-top: 2px;
}

.aa {
	box-sizing: border-box;
	height: 150px;
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
	cursor: pointer;
}

.text {
	color: #fff;
	font-size: 14px;
	font-family: 'Operator Mono', 'Source Code Pro', Menlo, Monaco, Consolas, Courier New, monospace;
	position: absolute;
	bottom: 5px;
	right: 5px;
}

.copy-box {
	width: 90px;
	height: 40px;
	box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
	border-radius: 4px;
	color: #ffffff;
	font-size: 17px;
	font-family: 'Operator Mono', 'Source Code Pro', Menlo, Monaco, Consolas, Courier New, monospace;
	text-align: center;
	line-height: 40px;
}

main {
	/*拷贝动画*/
	width: 100vw;
	height: 100vh;
	position: fixed;
	top: 0;
	left: 0;
}

.main-box1 {
	width: 100%;
	height: 200px;
	background-color: rgba(255, 255, 255, 0.1);
	position: absolute;
	top: 30vh;
	color: #ffffff;
	text-align: center;
	line-height: 200px;
	font-size: 100px;
	font-weight: bold;
	font-family: 'Operator Mono', 'Source Code Pro', Menlo, Monaco, Consolas, Courier New, monospace;
	animation: main-box1 1s;
}

.main-box2 {
	position: absolute;
	top: 70vh;
	width: 100%;
	color: #ffffff;
	text-align: center;
	font-size: 20px;
	font-weight: bold;
	font-family: 'Operator Mono', 'Source Code Pro', Menlo, Monaco, Consolas, Courier New, monospace;
}

@keyframes main-box1 {
	0%{
		transform: scale(1.2);
	}
	50%{
		transform: scale(1);
	}
	100%{
		transform: scale(0.7);
	}
}

</style>