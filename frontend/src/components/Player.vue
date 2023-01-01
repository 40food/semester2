<template>
  <v-card
    class="mx-auto"
    max-width="344"
    outlined
  >
    <v-list-item three-line>
      <v-list-item-content>
        <div class="text-overline mb-4">
          {{value.type}}
        </div>
        <v-list-item-title class="text-h5 mb-1" v-if="editMode">
          <v-text-field v-model="value.name"></v-text-field>
        </v-list-item-title>
        <v-list-item-title class="text-h5 mb-1" @click="toggleEditMode" v-else>
          {{value.name}}
        </v-list-item-title>
        <v-list-item-subtitle @click="toggleEditMode">힘은 {{value.power}}이고 빠르기는 {{value.speed}}입니다.</v-list-item-subtitle>
      </v-list-item-content><!--player의 power와 speed를 표시-->

      <v-list-item-avatar
        tile
        size="80"
        color="grey"
      ></v-list-item-avatar>
    </v-list-item>

    <v-card-actions>
      <v-btn
        outlined
        rounded
        text

        @click="training"
      ><!--training을 실행하는 버튼-->
        근력 트레이닝
      </v-btn>
      <v-btn
        outlined
        rounded
        text

        @click="running"
      ><!--running을 실행하는 버튼-->
        달리기
      </v-btn>

      <slot name="buttons"></slot>

    </v-card-actions>
  </v-card>

</template>

<script>
  const axios=require('axios').default;//프론트엔드와 백엔드를 연결하기 위해 필요

  export default {
    name: 'Player',

    props:{
      value: Object,
      editMode: Boolean
    },

    methods:{
      //해당 클래스 Object의 path에서 pathname만을 떼어와 새 URL을 만들고 기능을 실행
      async training(){
        await axios.request({
          method: 'PUT',
          url: new URL(this.value._links.training.href).pathname,
          headers:{'Content-Type':'application/json'}
        });

        this.refresh();
      },
      //해당 클래스 Object의 path에서 pathname만을 떼어와 새 URL을 만들고 기능을 실행
      async running(){
        await axios.request({
          method: 'PUT',
          url: new URL(this.value._links.running.href).pathname,
          headers:{'Content-Type':'application/json'}
        });

        this.refresh();
      },

      //실행된 기능의 결과가 바로 보이도록 새로고침을 자동으로 해줌
      async refresh(){
        var response=await axios.get(new URL(this.value._links.self.href).pathname);
        
        if(response){
          this.value=response.data;
          this.$emit("input",this.value);
        }
      },

      //이름을 바꾸게 해줌
      toggleEditMode(){
        this.editMode = !this.editMode;
        
        this.$emit("change", this.value);
      }

    }
  }
</script>
