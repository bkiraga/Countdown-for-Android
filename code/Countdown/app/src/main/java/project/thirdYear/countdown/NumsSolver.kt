package project.thirdYear.countdown

import android.text.TextUtils.substring

class NumsSolver {

    fun next_op(n:Int): ArrayList<String>{
        var stack = arrayListOf<String>()
        stack.add("+" + n.toString())
        stack.add("-" + n.toString())
        stack.add("*" + n.toString())
        stack.add("/" + n.toString())
        return stack
    }

    fun permutation(numList:ArrayList<Int>):ArrayList<ArrayList<Int>> {
        var perms = ArrayList<ArrayList<Int>>()
        perms.add(ArrayList<Int>())
        for (i in 0 until numList.size)
        {
            var current = ArrayList<ArrayList<Int>>()
            for (l in perms)
            {
                for (j in 0 until l.size + 1)
                {
                    l.add(j, numList.get(i))
                    var temp = ArrayList<Int>(l)
                    current.add(temp)
                    l.removeAt(j)
                }
            }
            perms = ArrayList<ArrayList<Int>>(current)
        }
        return perms
    }



    fun solve(nums:ArrayList<Int>, target:Int): String {

        var operators = mapOf<String, (Int, Int) -> Int>(
                "+" to { x: Int, y: Int -> x + y },
                "-" to { x: Int, y: Int -> x - y },
                "*" to { x: Int, y: Int -> x * y },
                "/" to { x: Int, y: Int -> x / y })

        var listOfLists = permutation(nums)

        for (list in listOfLists) {

            var second: ArrayList<String> = next_op(list.get(1))
            var third: ArrayList<String> = next_op(list.get(2))
            var fourth: ArrayList<String> = next_op(list.get(3))
            var fifth: ArrayList<String> = next_op(list.get(4))
            var sixth: ArrayList<String> = next_op(list.get(5))

            var num: Int = list.get(0)

            for (s in second) {
                var s_total = (operators[s.substring(0,1)]).invoke(num,s.substring(1).toInt())
                for (t in third) {
                    var t_total = (operators[t.substring(0,1)]).invoke(s_total,t.substring(1).toInt())
                    for (o in fourth) {
                        var o_total = (operators[o.substring(0,1)]).invoke(t_total,o.substring(1).toInt())
                        for (i in fifth) {
                            var i_total = (operators[i.substring(0,1)]).invoke(o_total,i.substring(1).toInt())
                            for (x in sixth) {
                                var x_total = (operators[x.substring(0,1)]).invoke(i_total,x.substring(1).toInt())
                            }

                        }
                    }
                }
            }
        }
    }

}