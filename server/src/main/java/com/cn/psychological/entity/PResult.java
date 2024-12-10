package com.cn.psychological.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 评测信息信息表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PResult {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;


    /**
     * 评测用户id
     */
    private String userId;

    /**
     * 性格
     */
    private String type;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 评测性格
     */
    @TableField(exist = false)
    private String typeName;

    /**
     * 评测内容
     */
    @TableField(exist = false)
    private String content;


    /**
     * 用户姓名
     */
    @TableField(exist = false)
    private String userName;



    public void setType(String type) {
        this.type = type;

        if("istj".equals(type)){
            this.typeName = "守护者";
            this.content = "你是一个认真负责、有组织能力强的人。你喜欢按照计划行事，对细节非常关注。别人常常会寻求你的建议，因为他们知道你总是能够提供可靠和实际的意见。";
        }else if("isfj".equals(type)){
            this.typeName = "慈爱者";
            this.content = "你是一个关心他人、喜欢帮助别人的人。你温暖、体贴，总是愿意为了他人的幸福而付出。你喜欢维护社会和谐，是一个值得信赖的朋友和同事。";
        }else if("infj".equals(type)){
            this.typeName = "信仰者";
            this.content = "你是一个深思熟虑、充满理想和信念的人。你对于人类发展和社会进步充满关心，常常追求有深度的人际关系。你是一个理解力强且富有创造性的思考者。";
        }else if("intj".equals(type)){
            this.typeName = "架构师";
            this.content = "你是一个目标明确、聪明且富有计划的人。你追求知识，注重效率，总是能够找到解决问题的创新途径。你是一个坚定自信的决策者。";
        }else if("istp".equals(type)){
            this.typeName = "冒险家";
            this.content = "你是一个喜欢冒险、独立思考的人。你喜欢解决问题，擅长运用实际技能。你对于挑战充满热情，总是在寻找新的经验和活动。";
        }else if("isfp".equals(type)){
            this.typeName = "艺术家";
            this.content = "你是一个充满艺术天赋、对美感极为敏感的人。你喜欢追求个人价值观，对于自己的创意有着深厚的热情。你是一个宽容、和善的朋友。";
        }else if("infp".equals(type)){
            this.typeName = "梦想家";
            this.content = "你是一个理想主义者，对于人类的进步和社会的公正充满热情。你是一个富有创造性的思想家，总是追求内心的梦想。你关心他人，并愿意为了理念而奉献。";
        }else if("intp".equals(type)){
            this.typeName = "学者";
            this.content = "你是一个聪明、好奇心旺盛的人。你喜欢独立思考，追求知识和理解事物的本质。你是一个富有创造性的问题解决者。";
        }else if("estp".equals(type)){
            this.typeName = "实干家";
            this.content = "你是一个行动派，喜欢追求刺激和新鲜感。你是一个敢于冒险的人，总是乐于尝试新的事物。你擅长解决问题，注重实际的结果。";
        }else if("esfp".equals(type)){
            this.typeName = "表演者";
            this.content = "你是一个充满活力、喜欢社交的人。你喜欢在人群中引起注意，享受生活的乐趣。你是一个乐观、友好的人，总是愿意带动气氛。";
        }else if("enfp".equals(type)){
            this.typeName = "洞察者";
            this.content = "你是一个充满热情、充满创意的人。你对于人际关系充满关怀，总是能够从不同的角度看待问题。你是一个乐观主义者，喜欢追求梦想。";
        }else if("entp".equals(type)){
            this.typeName = "发现者";
            this.content = "你是一个聪明、喜欢挑战的人。你善于思考和创新，总是能够找到新的解决方案。你是一个富有幽默感的人，对于新奇的事物充满好奇心。";
        }else if("estj".equals(type)){
            this.typeName = "主宰者";
            this.content = "你是一个组织有序、有领导才能的人。你喜欢规划和组织，对于达成目标有着坚定的决心。你是一个值得信赖、负责任的领导者。";
        }else if("esfj".equals(type)){
            this.typeName = "提供者";
            this.content = "你是一个热心、关心他人的人。你喜欢为他人提供支持，是一个出色的团队成员。你注重人际关系，总是愿意为他人着想。";
        }else if("enfj".equals(type)){
            this.typeName = "教育家";
            this.content = "你是一个热情、关心他人的领导者。你喜欢帮助他人实现潜力，是一个出色的团队合作者。你对于社会问题充满关切，追求共同的理想。";
        }else if("entj".equals(type)){
            this.typeName = "指导者";
            this.content = "你是一个目标明确、果断的领导者。你善于组织和规划，总是能够达成设定的目标。你是一个自信、具有影响力的人，能够激发他人的潜力。";
        }
    }
}
