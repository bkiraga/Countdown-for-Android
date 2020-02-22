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

    fun solve(nums:ArrayList<Int>, target:Int) {

        var operators = mapOf<String,(Int,Int)->Int>(
                "+" to {x:Int, y:Int -> x + y},
                "-" to {x:Int, y:Int -> x - y},
                "*" to {x:Int, y:Int -> x * y},
                "/" to {x:Int, y:Int -> x / y})

        var second: ArrayList<String> = next_op(nums.get(1))
        var third: ArrayList<String> = next_op(nums.get(2))
        var fourth: ArrayList<String> = next_op(nums.get(3))
        var fifth: ArrayList<String> = next_op(nums.get(4))
        var sixth: ArrayList<String> = next_op(nums.get(5))

        var num: Int = nums.get(0)

        for (s in second){
            for (t in third){
                for (o in fourth){
                    for (i in fifth){
                        for (x in sixth){

                        }

                    }
                }
            }
        }
    }

}