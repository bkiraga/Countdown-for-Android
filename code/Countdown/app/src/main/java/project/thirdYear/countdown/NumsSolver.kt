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
                for (t in third) {
                    for (o in fourth) {
                        for (i in fifth) {
                            for (x in sixth) {

                            }

                        }
                    }
                }
            }
        }
    }

}