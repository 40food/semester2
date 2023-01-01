<template>
  <v-data-table
    :headers="headers"
    :items="teamMembers"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>
  <!--표 탬플릿-->
</template>

<script>
const axios=require('axios').default;

export default {
  name: 'Team',

  components: {
  },

  data: () => ({
      headers:[
        {text: '이름', value: 'playerDetail.name'},
        {text: '힘', value: 'playerDetail.power'},
        {text: '빠르기', value: 'playerDetail.speed'},
        {text: '계약방법', value: 'contract.method'},
        {text: '계약상태', value: 'contract.status'}
      ],//헤더 설정
      teamMembers:[//teamMember가 들어갈 자리
      ],
      showTeam: false//다시 false로 만들어둠(초기화)
    }),

  created(){
    this.init();
  },

    methods:{
      async init(){
        const response = await axios.get("/teamMembers");
        this.teamMembers=response.data._embedded.teamMembers;
        //백엔드 teammembers의 정보를 가져와 프론트엔드와 연결

        this.teamMembers.forEach(async teamMember => {
          const response = await axios.get(new URL(teamMember._links.player.href).pathname);
          teamMember.playerDetail=response.data;
        })
        //람다 표현식으로 teamMember마다 pathname을 가져와 정보를 붙이도록 함
      }
    }
};
</script>
