# from flask import Flask,render_template,request
# import datetime
#
# app = Flask(__name__)
#
#
# # @app.route('/')
# # def hello_world():#系统自带的函数
# #     return '你好!'
#
# @app.route("/index")
# def hello():#自己定义的函数
#     return "hello"
#
# @app.route("/user/<name>")
# def username(name):#访问路径中带有参数，参数是字符串
#     return "你好，%s"%name;
#
# @app.route("/user/<int:age>")
# def userage(age):#访问路径中带有参数，参数是整形数
#     return "你好，你的年龄是%d"%age
#
# @app.route("/user/<float:salary>")
# def usersalary(salary):#访问路径中带有参数，参数是浮点数
#     return "你好，你的薪资是%.2f"%salary
#
# # @app.route("/")
# def index():#返回一个页面
#     time = datetime.date.today()
#     list = ["tom","rose","jack"]
#     task = {"任务":"洗衣服", "时间":"中午"}
#     return render_template("index1.html", time1=time, list1=list, task1=task)
#
# @app.route("/regist")
# def regist():
#     return render_template("regist.html")
#
# @app.route("/result", methods=["post", "get"])
# def result():
#     result = request.form
#     return render_template("result.html", result=result)
#
# if __name__ == '__main__':
#     app.run()
#
