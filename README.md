# calculator

## v1 2022.10.03
增加 java Calculator类

## v2 2022.10.06
完善Calculator类，支持包含**小数点**和**括号**的运算表达式计算；完成计算器xml布局图；
完成计算器业务逻辑的编写。 经测试，能够实现计算器的功能。

## v3 2022.10.30
① 加入横屏功能，且切换不同方向时数据不会丢失。

② 增加异常捕获，当输入不合语法的计算表达式时会跳出“Error!”，提升程序健壮性。

③ 加入求任意次**幂**(**^**)和任意整数**阶**(**!**)功能, 并且**可以融合进整体表达式**中。

   试例：(0.1)^2.5+3^5.0+4!*5.25^(3+2.5) = 219567.642 （正确）

④ 加入**计算任意定积分**功能。在横屏模式下，先按积分键，
然后输入变量表达式，再输入积分上下限，然后输入积分计算精度，
便可以得到该定积分结果。

## v4 2022.11.06
增加 使用ViewModel类保存了历史数据的功能，其中历史数据用字符串动态数组(ArrayList)保存。

## v5 2022.11.13
增加了查看计算器所有计算历史的功能。将计算历史数据传入HistoryActivity后，
**动态增加TextView控件**，每个TextView控件展示一条历史计算数据。
并且HistoryActivity使用ScrollView，确保用户能够查看所有历史记录。

## v6 2022.11.27
将原来在MainActivity的计算器页面, 用单独一个Fragment(1个java文件和2个Layout)独立分隔出来,
原来的MainActivity.java只留有很少一部分代码, 从而保持简洁. 
同时, 由于目前尚且未学到Fragment如何传递数据, 所以HistoryActivity的历史计算记录功能暂时取消，等下个版本再完善。

## v7 2022.11.28
将显示历史数据的功能改为了使用RecyclerView。

添加fragment_calculate的land版本，让项目更规范。

## v8 2022.12.05
将HistoryActivity去掉, 计算器只用两个Fragment和MainActivity实现计算页面和历史计算页面。

同时，实现了点击计算历史记录，可以跳转到有对应算术表达式的计算器页面的功能。

但目前有个BUG本人无法解决：就是在初始CalculateInterfaceFragment时被创造时，调用了一次getActivity().addMenuProvider(this)，
在从HistoryListFragment使用**Navigate**跳转到CalculateInterfaceFragment时，CalculateInterfaceFragment又被创造，于是又调用了一次getActivity().addMenuProvider(this)，
这导致MainActivity页面的**菜单栏里有两个History**。

