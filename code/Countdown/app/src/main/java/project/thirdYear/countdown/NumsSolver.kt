package project.thirdYear.countdown

import android.util.Log

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



    fun solve(nums:ArrayList<Int>, target:Int): ArrayList<String> {

        var result = arrayListOf<String>()

        var operators = mapOf<String, (Double, Int) -> Double>(
                "+" to { x: Double, y: Int -> x + y },
                "-" to { x: Double, y: Int -> x - y },
                "*" to { x: Double, y: Int -> x * y },
                "/" to { x: Double, y: Int -> x / y })

        var listOfLists = permutation(nums)

        for (list in listOfLists) {

            var second: ArrayList<String> = next_op(list.get(1))
            var third: ArrayList<String> = next_op(list.get(2))
            var fourth: ArrayList<String> = next_op(list.get(3))
            var fifth: ArrayList<String> = next_op(list.get(4))
            var sixth: ArrayList<String> = next_op(list.get(5))

            var num: Int = list.get(0)

            for (s in second) {
                var s_total = (operators[s.substring(0,1)])!!.invoke(num.toDouble(),s.substring(1).toInt())
                Log.d(TAG, "s_total $s_total, numTd : ${num.toDouble()}, s.subConv: ${s.substring(1).toInt()} s.sub:${s.substring(1)}")
                if (s_total == target.toDouble() && ((operators[s.substring(0,1)])!!.invoke(num.toDouble(),s.substring(1).toInt())) % 1 == 0.0){
                    if (num == 7){
                        Log.d(TAG,"DebuggingSome: numTd: ${num.toDouble()}, s.subConv: ${s.substring(1).toInt()}, s.sub: ${s.substring(1)}, s_total= ${s_total}")
                    }


                    result.add(num.toString() + s.substring(0,1) + s.substring(1) + "=" + s_total.toString() + "\n")
                }
                for (t in third) {

                    var t_total = (operators[t.substring(0,1)])!!.invoke(s_total.toDouble(),t.substring(1).toInt())
                    Log.d(TAG, "$t_total")
                    if (t_total == target.toDouble() && (operators[t.substring(0,1)])!!.invoke(s_total.toDouble(),t.substring(1).toInt()) % 1 == 0.0){

                        result.add(num.toString() + s.substring(0,1) + s.substring(1) + t.substring(0,1) + t.substring(1) + "=" + t_total.toString()+ "\n")
                    }
                    for (o in fourth) {

                        var o_total = (operators[o.substring(0,1)])!!.invoke(t_total.toDouble(),o.substring(1).toInt())
                        Log.d(TAG, "$o_total")
                        if (o_total == target.toDouble() && t_total.toDouble() % (operators[o.substring(0,1)])!!.invoke(t_total.toDouble(),o.substring(1).toInt()) % 1 == 0.0){

                            result.add(num.toString() + s.substring(0,1) + s.substring(1) + t.substring(0,1) + t.substring(1) + o.substring(0,1) + o.substring(1) + "=" + o_total.toString()+ "\n")
                        }
                        for (i in fifth) {

                            var i_total = (operators[i.substring(0,1)])!!.invoke(o_total.toDouble(),i.substring(1).toInt())

                            if (i_total == target.toDouble() && (operators[i.substring(0,1)])!!.invoke(o_total.toDouble(),i.substring(1).toInt()) % 1 == 0.0){

                                result.add(num.toString() + s.substring(0,1) + s.substring(1) + t.substring(0,1) + t.substring(1) + o.substring(0,1) + o.substring(1) + i.substring(0,1) + i.substring(1)+ "=" + i_total.toString()+ "\n")
                            }
                            for (x in sixth) {

                                var x_total = (operators[x.substring(0,1)])!!.invoke(i_total.toDouble(),x.substring(1).toInt())

                                if (x_total == target.toDouble() && (operators[x.substring(0,1)])!!.invoke(i_total.toDouble(),x.substring(1).toInt()) % 1 == 0.0){
                                    result.add(num.toString() + s.substring(0,1) + s.substring(1) + t.substring(0,1) + t.substring(1) + o.substring(0,1) + o.substring(1) + i.substring(0,1) + i.substring(1) + x.substring(0,1) + x.substring(1) + "=" + x_total.toString()+ "\n")
                                }
                            }

                        }
                    }
                }
            }
        }
        return result
    }

    companion object{
        private val TAG = "Solver"
    }

}