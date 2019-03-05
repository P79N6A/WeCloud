#!/usr/bin/env python
# -*- coding: utf-8 -*-
#告诉Linux系统这是一个可以执行的文件，window忽略
#楼上这一句用来使py支持中文的
import math
import os
#print("http")
#name=input('please input your name:')
#print("i age:",name)
#age=input('please input your age:')
#if age >= 18:
#    print('you are big people')
#else:
#    print('small people')
#print('I\'m %s,my years old is %d,PI is %f' %('caoke',23,1.2))
#格式化输出，字符串%s,数字%d,浮点型%f,用%()来表示参数，中文需要用''包围
#print('Hi,{0},your score is {1}'.format('中文',59))
#classmates=['caoke','liyong','liuyanlei']
#print(classmates)
#print(classmates[1])
#print(classmates[-1])
#classmates.insert(1,'wupengfei')
#print(classmates)
#classmates.pop()
#print(classmates)
#classmates.pop(1)
#print(classmates)
#classmates.insert(1,['wupengfei','liuyanlei'])
#print(classmates)
#print(classmates[1])
#courses=('yuwen','shuxue','yinyu')
#print(courses)
#t=(1,)
#print(t)
#age=20
#if age>18:
#    print("age is",age)
#score=input('please input score:')
#s=int(score)
#if score>60:
#    print('pass')
#elif score>80:
#    print('high score')
#else:
#    print('sb')
#lr=list(range(5))
#print(lr)
#for l in lr:
#    print(l)
#sum=0
#n=0
#while n<10:
#    sum=sum+n
#    n=n+1
#print(sum)
#i=1
#while i<100:
#    if i==10:
#        break
#    print(i)
#    i=i+1
#dis={"caoke":34,"liyong":45}
#print(dis['caoke'])
#if 'l' in dis:
#    print(dis['l'])
#else:
#    print("0")
#print(dis.get("l",-1))
#dis['wupengfei']=29
#print(dis)
#dis.pop('liyong')
#print(dis)
#a=(2,3,4)
#dis[a]='23'
#print(dis)
#b=(2,3,[3,4])
#print(dis)
#s=set([2,3,3,4])
#print(s)
#print(s.remove(3))
#print(s)
#h=hex(23)
#print(h)
#def myabs(x):
#    if not isinstance(x,(int,float)):
#        raise TypeError("bad type")
#    if x>0:
#        return x
#    else:
#        return -x
#ab = myabs(-1)
#print(ab)
#ac = myabs(a)
#print(ac)
#print(math.sqrt(2))
#def power(x):
#    return x*x;
#print(power(2))
#def power(x,n=2):
#    s=1
#    while n>0:
#        n=n-1
#        s=s*x
#    return s
#print(power(2,3))
#print(power(2))
#def call(*nums):
#    for i in nums:
#        print(i)
#t=(1,2,3)
#call(*t)
#L=range(100)
#print(L)
#print(L[0:10])
#print(L[20:30])
#print(L[-10:])
#print(L[:5])
#R=L[:]
#print(R)
#print(R[::2])
#S="String"
#print(S[:2])
#dirlist=os.listdir(".")
#print(dirlist)
#for x in dirlist:
#    print(x)
#dirs=[x for x in os.listdir(".")]
#print(dirs)
#map={"a":1,"b":2,"c":3}
#for x,y in map.items():
#    print(x,y)
#L1 = ['Hello', 'World', 18, 'Apple', None]
##L2=[if isinstance(x,(str)): x.lower() for x in L1]
#L2 = [s.lower() for s in L1 if isinstance(s,str)]
#print(L2)
#def f(x):
#    return x*x
#r=map(f,[1,2,3,4])
#print(r)
#def f1(x,y):
#    return x*10+y
#s=reduce(f1,[1,3,5,7,9])
#print(s)
origin=['adam', 'LISA', 'barT']
def f(x):
    newV=""
    for i,v in enumerate(x):
        if i==0:
            newV=newV+v.upper();
        else:
            newV=newV+v.lower();
    return newV
simple=map(f,origin)
print(simple)

def f(x,y):
    return x*y
def prod(L):
    j=reduce(f,L)
    return j
print(prod([1,2,3]))
























