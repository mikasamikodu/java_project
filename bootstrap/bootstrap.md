### 容器
	1.流体容器
	2.固体容器
		阈值  								width(槽宽是固定的30px)
		>=1200px			(lg 大屏pc)		1170px(1140+槽宽)
		<1200px and >=992px (md 中屏pc)      970px (940+槽宽)
		<992px and >=768px  (sm 平板)	     750px (720+槽宽)
		<768px              (移动手机)		 auto
	3.栅格系统
### 栅格源码分析
	1.流体容器与固体容器的公共样式
		margin-right:  auto;
		margin-left:   auto;
		padding-left:  15px;
		padding-right: 15px;
	2.固体容器特定样式
		顺序不可变，必须从上到下
		@media (min-width: @screen-sm-min) {
		  width: @container-sm;
		}
		@media (min-width: @screen-md-min) {
		  width: @container-md;
		}
		@media (min-width: @screen-lg-min) {
		  width: @container-lg;
		}
	3.行
		margin-left:  -15px;
		margin-right: -15px;
	4.列
		.make-grid-columns();--->>
								||
								\/
		.col-xs-1, .col-sm-1, .col-md-1,.col-lg-1,
		 .col-xs-2, .col-sm-2, .col-md-2, .col-lg-2,
		 col-xs-3, .col-sm-3, .col-md-3, .col-lg-3,
		 ...,
		 col-xs-12, .col-sm-12, .col-md-12, .col-lg-12{
			 position: relative;
			 min-height: 1px;
			 padding-left:  15px;
			 padding-right: 15px;
		 }
		 .make-grid(xs);--->>
						   ||
						   \/
		   .make-grid(@class) {
		     .float-grid-columns(@class);
					.col(13, ~".col-xs-1,.col-xs-2,....col-xs-12{
								float: left;
					}
		     .loop-grid-columns(@grid-columns, @class, width);
					.col-xs-12 {
					  width: 12/12;
					}
					.col-xs-11 {
					  width: 11/12;
					}
					...
					.col-xs-1 {
					  width: 1/12;
					}
		     .loop-grid-columns(@grid-columns, @class, pull);
					.col-xs-pull-12 {
					  right: 12/12;
					}
					.col-xs-pull-11 {
					  right: 11/12;
					}
					...
					.col-xs-pull-1 {
					  right: 1/12;
					}
					.col-xs-pull-0 {
					  right: auto;
					}
		     .loop-grid-columns(@grid-columns, @class, push);
					.col-xs-push-12 {
					  left: 12/12;
					}
					.col-xs-push-11 {
					  left: 11/12;
					}
					...
					.col-xs-push-1 {
					  left: 1/12;
					}
					.col-xs-push-0 {
					  left: auto;
					}
		     .loop-grid-columns(@grid-columns, @class, offset);
					.col-xs-offset-12 {
					  margin-left: 12/12;
					}
					.col-xs-offset-11 {
					  margin-left: 11/12;
					}
					...
					.col-xs-offset-1 {
					  margin-left: 1/12;
					}
					.col-xs-offset-0 {
					  margin-left: 0;
					}
		   }
### 响应式工具
### 栅格盒模型设计的精妙之处
		容器两侧右15px的padding
		行  两侧有-15px的margin
		列  两侧有15px的padding
		为了维护槽宽的规则，就必须要有列两侧的padding
		为了能使列嵌套行，行两侧就必须有-15的margin
		为了让容器包裹住行，容器两侧就必须要有15px的padding