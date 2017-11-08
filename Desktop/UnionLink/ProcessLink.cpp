//两个链表合并为一个 
#include<stdio.h>
#include<stdlib.h>
# define N 5
typedef struct node{
	int data;
	struct node *next;
}ElemSN;
ElemSN *CreateLink(int a[]){
	ElemSN *h,*tail,*p;
	h=tail=(ElemSN *)(malloc(sizeof(ElemSN)));
	h->data=a[0];
	h->next=NULL;
	for(int i=1;i<N;i++){
		p=(ElemSN *)(malloc(sizeof(ElemSN)));
		p->data=a[i];
		p->next=NULL;
		tail=tail->next=p;
		return h;
	}
}
ElemSN *Process(ElemSN *h1,ElemSN *h2){
	ElemSN *ins,*hn,*t;
	hn=NULL;
	while(h1&&h2){
		if(h1->data<h2->data){
			ins=h1;
			h1=h1->next;
		}else{
			ins=h2;
			h2=h2->next;
		}
		if(!hn){
			hn=t=ins;
		}else{
			t=t->next=ins;
		}
		if(!h1){
			t->next=h2;
		}else{
			t->next=h1;
		}
	}
}

	
	
	
	
	
	
	

