package com.munzenberger.advent;

import java.util.List;

public class Main {

	public static void main(String[] args) {

		println("Advent of Code 2017 Solutions (http://adventofcode.com/2017)");

		println("--- Day 1: Inverse Captcha ---");

		final String DAY1_INPUT = "823936645345581272695677318513459491834641129844393742672553544439126314399846773234845535593355348931499496184839582118817689171948635864427852215325421433717458975771369522138766248225963242168658975326354785415252974294317138511141826226866364555761117178764543435899886711426319675443679829181257496966219435831621565519667989898725836639626681645821714861443141893427672384716732765884844772433374798185955741311116365899659833634237938878181367317218635539667357364295754744829595842962773524584225427969467467611641591834876769829719248136613147351298534885563144114336211961674392912181735773851634298227454157885241769156811787611897349965331474217223461176896643242975397227859696554492996937235423272549348349528559432214521551656971136859972232854126262349381254424597348874447736545722261957871275935756764184378994167427983811716675476257858556464755677478725146588747147857375293675711575747132471727933773512571368467386151966568598964631331428869762151853634362356935751298121849281442128796517663482391226174256395515166361514442624944181255952124524815268864131969151433888721213595267927325759562132732586252438456569556992685896517565257787464673718221817783929691626876446423134331749327322367571432532857235214364221471769481667118117729326429556357572421333798517168997863151927281418238491791975399357393494751913155219862399959646993428921878798119215675548847845477994836744929918954159722827194721564121532315459611433157384994543332773796862165243183378464731546787498174844781781139571984272235872866886275879944921329959736315296733981313643956576956851762149275521949177991988236529475373595217665112434727744235789852852765675189342753695377219374791548554786671473733124951946779531847479755363363288448281622183736545494372344785112312749694167483996738384351293899149136857728545977442763489799693492319549773328626918874718387697878235744154491677922317518952687439655962477734559232755624943644966227973617788182213621899579391324399386146423427262874437992579573858589183571854577861459758534348533553925167947139351819511798829977371215856637215221838924612644785498936263849489519896548811254628976642391428413984281758771868781714266261781359762798";

		println("Part One: %s", InverseCaptcha.solvePart1(DAY1_INPUT));
		println("Part Two: %s", InverseCaptcha.solvePart2(DAY1_INPUT));

		println("--- Day 2: Corruption Checksum ---");

		final List<int[]> DAY2_INPUT = CorruptionChecksum.parse(
				"179	2358	5197	867	163	4418	3135	5049	187	166	4682	5080	5541	172	4294	1397\r\n" + 
				"2637	136	3222	591	2593	1982	4506	195	4396	3741	2373	157	4533	3864	4159	142\r\n" + 
				"1049	1163	1128	193	1008	142	169	168	165	310	1054	104	1100	761	406	173\r\n" + 
				"200	53	222	227	218	51	188	45	98	194	189	42	50	105	46	176\r\n" + 
				"299	2521	216	2080	2068	2681	2376	220	1339	244	605	1598	2161	822	387	268\r\n" + 
				"1043	1409	637	1560	970	69	832	87	78	1391	1558	75	1643	655	1398	1193\r\n" + 
				"90	649	858	2496	1555	2618	2302	119	2675	131	1816	2356	2480	603	65	128\r\n" + 
				"2461	5099	168	4468	5371	2076	223	1178	194	5639	890	5575	1258	5591	6125	226\r\n" + 
				"204	205	2797	2452	2568	2777	1542	1586	241	836	3202	2495	197	2960	240	2880\r\n" + 
				"560	96	336	627	546	241	191	94	368	528	298	78	76	123	240	563\r\n" + 
				"818	973	1422	244	1263	200	1220	208	1143	627	609	274	130	961	685	1318\r\n" + 
				"1680	1174	1803	169	450	134	3799	161	2101	3675	133	4117	3574	4328	3630	4186\r\n" + 
				"1870	3494	837	115	1864	3626	24	116	2548	1225	3545	676	128	1869	3161	109\r\n" + 
				"890	53	778	68	65	784	261	682	563	781	360	382	790	313	785	71\r\n" + 
				"125	454	110	103	615	141	562	199	340	80	500	473	221	573	108	536\r\n" + 
				"1311	64	77	1328	1344	1248	1522	51	978	1535	1142	390	81	409	68	352");

		println("Part One: %d", CorruptionChecksum.solvePart1(DAY2_INPUT));
		println("Part Two: %d", CorruptionChecksum.solvePart2(DAY2_INPUT));

		println("--- Day 3: Spiral Memory ---");

		final int DAY3_INPUT = 265149;

		println("Part One: %d", SpiralMemory.solvePart1(DAY3_INPUT));
	}

	private static void println(String format, Object...args) {
		System.out.println(String.format(format, args));
	}
}
