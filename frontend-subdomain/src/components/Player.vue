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
        <v-list-item-title class="text-h5 mb-1">
          {{value.name}}
        </v-list-item-title>
        <v-list-item-subtitle>힘은 {{value.power}}이고 빠르기는 {{value.speed}}입니다.</v-list-item-subtitle>
      </v-list-item-content><!--player의 power와 speed를 표시-->

      <v-list-item-avatar
        tile
        size="80"
        color="grey"
      ></v-list-item-avatar>
    </v-list-item>

    <v-card-actions>

      <v-chip v-if="inTheTeam">스카우트 완료</v-chip>
      <v-btn
        v-else
        outlined
        rounded
        text

        @click="addToTeam"
      >
        스카우트
      </v-btn>
      <!--inTheTeam이 트루면 스카우트 완료가 뜨도록, 아니면 스카우트 버튼이 뜨도록 함-->

      <slot name="buttons"></slot>

    </v-card-actions>
  </v-card>

</template>

<script>
  const axios=require('axios').default;

  export default {
    name: 'Player',

    props:{
      value: Object,
      editMode: Boolean
    },

    data:()=>{
      return{
        inTheTeam: false //초기화
      }
    },

    methods:{
      async addToTeam(){

        var teamMember={
          player: this.value._links.self.href,
          club:"http://localhost:8088/clubs/seido",
          contract: {
            method: "임시계약",
            status: "계약완료"
          }//구단명, 계약방법, 계약상태를 입력해둠
        }

        await axios.request({
          method: 'POST',
          url: "/teamMembers",
          headers:{'Content-Type':'application/json'},
          data: teamMember
        });//teamMember에 추가

        this.inTheTeam=true;//스카우트 완료 chip으로 바뀌도록 true로 바꾸어줌
      },
    }
  }
</script>
