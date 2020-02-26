package project.thirdYear.countdown

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
                var operator1 = s.substring(0, 1)
                var num1 = s.substring(1)
                if (!(((operator1 == "-" && num < num1.toInt()) || ((operator1 == "/" && num.rem(num1.toInt()) != 0))))) {
                    var s_total = operators[operator1]!!.invoke(num, num1.toInt())
                    if (s_total == target) {
                        result.add(num.toString() + operator1 + num1 + "=" + s_total.toString() + "\n")
                    }
                    for (t in third) {
                        var operator2 = t.substring(0, 1)
                        var num2 = t.substring(1)
                        if (!(((operator2 == "-" && s_total < num2.toInt()) || ((operator2 == "/" && s_total.rem(num2.toInt()) != 0))))) {
                            var t_total = operators[operator2]!!.invoke(s_total, num2.toInt())
                            if (t_total == target) {
                                result.add(num.toString() + operator1 + num1 + operator2 + num2 + "=" + t_total.toString() + "\n")
                            }
                            for (o in fourth) {
                                var operator3 = o.substring(0, 1)
                                var num3 = o.substring(1)
                                if (!(((operator3 == "-" && t_total < num3.toInt()) || ((operator3 == "/" && t_total.rem(num3.toInt()) != 0))))) {
                                    var o_total = operators[operator3]!!.invoke(t_total, num3.toInt())
                                    if (o_total == target) {
                                        result.add(num.toString() + operator1 + num1 + operator2 + num2 + operator3 + num3 + "=" + o_total.toString() + "\n")
                                    }
                                    for (i in fifth) {
                                        var operator4 = i.substring(0, 1)
                                        var num4 = i.substring(1)
                                        if (!(((operator4 == "-" && o_total < num4.toInt()) || ((operator4 == "/" && o_total.rem(num4.toInt()) != 0))))) {
                                            var i_total = operators[operator4]!!.invoke(o_total, num4.toInt())
                                            if (i_total == target) {
                                                result.add(num.toString() + operator1 + num1 + operator2 + num2 + operator3 + num3 + operator4 + num4 + "=" + i_total.toString() + "\n")
                                            }
                                            for (x in sixth) {
                                                var operator5 = x.substring(0, 1)
                                                var num5 = x.substring(1)
                                                if (!(((operator5 == "-" && i_total < num5.toInt()) || ((operator5 == "/" && i_total.rem(num5.toInt()) != 0))))) {
                                                    var x_total = operators[operator5]!!.invoke(i_total, num5.toInt())
                                                    if (x_total == target) {
                                                        result.add(num.toString() + operator1 + num1 + operator2 + num2 + operator3 + num3 + operator4 + num4 + operator5 + num5 + "=" + x_total.toString() + "\n")
                                                    }
                                                }
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result
    }

}